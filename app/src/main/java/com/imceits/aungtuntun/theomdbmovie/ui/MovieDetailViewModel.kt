package com.imceits.aungtuntun.theomdbmovie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.imceits.aungtuntun.theomdbmovie.data.MovieRepository
import com.imceits.aungtuntun.theomdbmovie.data.Movies
import com.imceits.aungtuntun.theomdbmovie.data.Resource
import com.imceits.aungtuntun.theomdbmovie.util.AbsentLiveData
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {
    private val movieId: MutableLiveData<Int> = MutableLiveData()
    val movieDetail : LiveData<Movies> = Transformations
        .switchMap(movieId) {id ->
            if (id == 0){
                AbsentLiveData.create()
            }else{
                movieRepository.getMovie(id)
            }
        }

    fun setParam(id: Int) {
        if (movieId.value != id){
            movieId.value = id
        }
    }
}
