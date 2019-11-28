package com.imceits.aungtuntun.theomdbmovie.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imceits.aungtuntun.theomdbmovie.ui.MovieDetailViewModel
import com.imceits.aungtuntun.theomdbmovie.ui.MovieListViewModel
import com.imceits.aungtuntun.theomdbmovie.viewmodelfactory.MovieViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: MovieViewModelFactory) : ViewModelProvider.Factory

    @Binds
    abstract fun bindContext(application: Application): Context

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    abstract fun bindMovieListViewModel(listViewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    abstract fun bindMovieDetailViewModel(detailViewModel: MovieDetailViewModel): ViewModel
}