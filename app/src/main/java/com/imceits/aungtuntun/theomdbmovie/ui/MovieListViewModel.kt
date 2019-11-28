package com.imceits.aungtuntun.theomdbmovie.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imceits.aungtuntun.theomdbmovie.data.MovieRepository
import com.imceits.aungtuntun.theomdbmovie.data.Movies
import com.imceits.aungtuntun.theomdbmovie.data.Resource
import javax.inject.Inject

class MovieListViewModel @Inject constructor(movieRepository: MovieRepository) : ViewModel() {

    val movieList: LiveData<Resource<List<Movies>>> = movieRepository.loadPopularMovies()

}
