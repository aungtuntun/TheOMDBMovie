package com.imceits.aungtuntun.theomdbmovie.di

import android.app.Application
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imceits.aungtuntun.theomdbmovie.api.MovieService
import com.imceits.aungtuntun.theomdbmovie.api.RequestInterceptor
import com.imceits.aungtuntun.theomdbmovie.data.MovieDao
import com.imceits.aungtuntun.theomdbmovie.data.MovieDatabase
import com.imceits.aungtuntun.theomdbmovie.util.Constants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideMovieService(gson: Gson, okHttpClient: OkHttpClient): MovieService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideDB(app: Application): MovieDatabase {
        return Room.databaseBuilder(app, MovieDatabase::class.java, "movies.db").build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(db: MovieDatabase): MovieDao {
        return db.movieDao()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.connectTimeout(Constants.TIME_OUT_SEC, TimeUnit.SECONDS)
            .readTimeout(Constants.TIME_OUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor())
        return okHttpClient.build()
    }

 /*   @Singleton
    @Provides
    fun provideMovieRepository(movieDao: MovieDao) : MovieRepository {
        return MovieRepository(movieDao)
    }*/
}