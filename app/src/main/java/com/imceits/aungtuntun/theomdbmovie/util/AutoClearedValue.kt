package com.imceits.aungtuntun.theomdbmovie.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.lang.IllegalStateException
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearedValue<T: Any>(val fragment: Fragment) : ReadWriteProperty<Fragment, T> {
    private var value: T? = null
    init {
        fragment.lifecycle.addObserver(object :LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                value = null
            }
        })
    }
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return value?: throw IllegalStateException("Not available value")
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
       this.value = value
    }

    fun <T: Any> Fragment.autoCleared() = AutoClearedValue<Fragment>(this)
}

