package com.imceits.aungtuntun.theomdbmovie.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import com.imceits.aungtuntun.theomdbmovie.AppExecutors
import com.imceits.aungtuntun.theomdbmovie.api.CreditResponse
import com.imceits.aungtuntun.theomdbmovie.api.MovieResponse
import com.imceits.aungtuntun.theomdbmovie.api.MovieService
import com.imceits.aungtuntun.theomdbmovie.api.NetworkBoundResource
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    val appExecutors: AppExecutors,
    val movieDao: MovieDao,
    val movieService: MovieService) {

    fun loadPopularMovies(): LiveData<Resource<List<Movies>>> {
        return object :NetworkBoundResource<List<Movies>, MovieResponse>(appExecutors) {
            @SuppressLint("CheckResult")
            override fun saveCallResult(item: MovieResponse?) {
                if(item != null) {
                    val size = item.results.size
                    for (i in 0 until size) {
                        val id = item.results[i].id
                 /*       val call = movieService.getMovieDetail(id)
                        val castCall= movieService.getCastDetail(id)
                        call.enqueue(object : Callback<Movies> {
                            override fun onFailure(call: Call<Movies>, t: Throwable) {
                                Log.e("Movie Throwable: " , t.message!!)
                            }

                            override fun onResponse( call: Call<Movies>,  response: Response<Movies> ) {
                                appExecutors.diskIO().execute {
                                    val data = response.body()
                                    if (data != null) {
                                        movieDao.insertMovie(data)
                                    }

                                }
                            }
                        })
                        castCall.enqueue(object : Callback<CreditResponse> {
                            override fun onFailure(call: Call<CreditResponse>, t: Throwable) {
                                Log.e("Credit ERROR: " , t.message!!)
                            }

                            override fun onResponse(  call: Call<CreditResponse>,  response: Response<CreditResponse>  ) {
                                appExecutors.diskIO().execute {
                                    val data = response.body()
                                    if (data?.cast != null) {
                                        movieDao.updateMovieCast(data.cast, id)
                                    }

                                }
                            }
                        })*/
                        val fullMovie: Observable<Movies> = Observables.combineLatest(
                            movieService.fetchMovieDetail(id.toString()),
                            movieService.fetchCastDetail(id.toString())) {
                                movies, creditResponse ->
                            movies.casts = creditResponse.cast
                            movies
                        }
                        fullMovie.subscribe({
                            movieDao.insertMovie(it)
                        }, {
                            Log.e("ERROR: " , it.message!!)
                        })
                     //   Log.i("Insert:", "$fullMovie")
                    }
                }


            }

            override fun shouldFetch(data: List<Movies>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun loadFromDb(): LiveData<List<Movies>> {
                return movieDao.getAllMovies()
            }

            override fun createCall(): Call<MovieResponse> {
                return movieService.loadMovie()
            }

        }.asLiveData()
    }

    fun getMovie(id: Int) : LiveData<Movies> {
        return movieDao.getMovie(id)
    }

}