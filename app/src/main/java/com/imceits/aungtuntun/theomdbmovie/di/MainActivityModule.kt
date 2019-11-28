package com.imceits.aungtuntun.theomdbmovie.di

import com.imceits.aungtuntun.theomdbmovie.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

 @ContributesAndroidInjector(modules = [FragmentModule::class])
internal abstract fun contributeMainActivity(): MainActivity
}