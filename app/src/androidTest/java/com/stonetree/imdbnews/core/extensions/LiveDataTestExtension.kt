package com.stonetree.imdbnews.core.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Lifecycle.Event.ON_RESUME
import org.mockito.Mockito.mock

/**
 *
 *  https://stackoverflow.com/questions/45949584/how-does-the-reified-keyword-in-kotlin-work
 *
 */

inline fun <reified T> lambdaMock(): T = mock(T::class.java)

fun <T>
        ViewModel.observeLiveData(name: String, observer: (T) -> Unit): MutableLiveData<T?> {

    val field = accessField(name) as LiveData<T>
    val mutable = MutableLiveData(field.value)
    mutable.observe({ createLifeCycle() }) { data ->
        data?.let(observer)
    }
    return mutable
}

fun createLifeCycle(): LifecycleRegistry {
    val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
    lifecycle.handleLifecycleEvent(ON_RESUME)
    return lifecycle
}
