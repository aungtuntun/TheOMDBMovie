package com.imceits.aungtuntun.theomdbmovie.api

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.imceits.aungtuntun.theomdbmovie.AppExecutors
import com.imceits.aungtuntun.theomdbmovie.data.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("LeakingThis")
abstract class NetworkBoundResource<ResultType, RequestType>
@MainThread constructor(private val appExecutors: AppExecutors) {

    private val result = MediatorLiveData<Resource<ResultType>>()
    init {
        result.value = Resource.loading(null)
        val dbSource = loadFromDb()
        result.addSource(dbSource) {data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            }else {
                result.addSource(dbSource) {newData ->
                    setValue(Resource.success(newData))
                }
            }
        }
    }
    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()
        result.addSource(dbSource) { newData ->
            setValue(Resource.loading(newData))
        }
        apiResponse.enqueue(object : Callback<RequestType> {
            override fun onFailure(call: Call<RequestType>, t: Throwable) {
                onFetchFailed()
                result.addSource(dbSource) {newData ->
                    setValue(Resource.error(t.message!!, newData))
                }
            }

            override fun onResponse(call: Call<RequestType>, response: Response<RequestType>) {
                appExecutors.diskIO().execute {
                    saveCallResult(processResponse(response.body()!!))
                    appExecutors.mainThread().execute {
                        result.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
            }

        })
   /*     apiResponse.enqueue(object : Callback<ApiResponse<RequestType>> {
            override fun onFailure(call: Call<ApiResponse<RequestType>>, t: Throwable) {
                onFetchFailed()
                result.addSource(dbSource) {newData ->
                    setValue(Resource.error(t.message!!, newData))
                }
            }

            override fun onResponse( call: Call<ApiResponse<RequestType>>,
                response: Response<ApiResponse<RequestType>> ) {
                appExecutors.diskIO().execute {
                    saveCallResult(processResponse(response))
                    appExecutors.mainThread().execute {
                        result.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
            }
        })*/
      /*  result.addSource(apiResponse) {response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            if(response!!.isSuccessful) {
                appExecutors.diskIO().execute {
                    saveCallResult(processResponse(response))
                    appExecutors.mainThread().execute {
                        result.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
            }else {
                onFetchFailed()
                result.addSource(dbSource) {newData ->
                    setValue(Resource.error(response.errorMessage!!, newData))
                }
            }
        }*/
    }

    protected open fun onFetchFailed(){}

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @WorkerThread
    protected open fun processResponse(response: RequestType) = response
    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType?)
    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>
    @MainThread
    protected abstract fun createCall(): Call<RequestType>
}