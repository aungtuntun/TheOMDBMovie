package com.imceits.aungtuntun.theomdbmovie.api

import androidx.lifecycle.LiveData
import com.imceits.aungtuntun.theomdbmovie.data.Movies
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.lang.StringBuilder

interface MovieService {

    @GET("movie/popular")
    fun loadMovie(): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun fetchMovieDetail(@Path("movie_id") movieId: String ): Observable<Movies>

    @GET("movie/{movie_id}/credits")
    fun fetchCastDetail(@Path("movie_id") movieId: String): Observable<CreditResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int) : Call<Movies>

    @GET("movie/{movie_id}/credits")
    fun getCastDetail(@Path("movie_id") movieId: Int) : Call<CreditResponse>


}