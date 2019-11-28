package com.imceits.aungtuntun.theomdbmovie.di

import android.app.Application
import com.imceits.aungtuntun.theomdbmovie.MovieApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, MainActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
         @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(movieApp: MovieApp)
}