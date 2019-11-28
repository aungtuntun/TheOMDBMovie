package com.imceits.aungtuntun.theomdbmovie

import android.app.Application
import com.imceits.aungtuntun.theomdbmovie.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import java.util.*
import javax.inject.Inject

class MovieApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
    }
    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}