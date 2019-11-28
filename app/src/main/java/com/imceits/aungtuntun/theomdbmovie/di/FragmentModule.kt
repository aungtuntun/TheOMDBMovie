package com.imceits.aungtuntun.theomdbmovie.di

import com.imceits.aungtuntun.theomdbmovie.ui.MovieDetailFragment
import com.imceits.aungtuntun.theomdbmovie.ui.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment
}