package com.stonetree.shuttergallery.core.livedata

import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.Event.*
import com.stonetree.shuttergallery.core.reflection.accessField
import org.mockito.Mockito.*

/**
 *
 *  https://stackoverflow.com/questions/45949584/how-does-the-reified-keyword-in-kotlin-work
 *
 */

inline fun<reified T> lambdaMock(): T = mock(T::class.java)

fun<T> ViewModel.observeLiveData(
    name: String,
    observer: (T) -> Unit
): MutableLiveData<T?>
{
    val field = accessField(name) as LiveData<T>
    val mutable = MutableLiveData(field.value)
    mutable.observe({ createLifeCycle() }) { data ->
        data?.let(observer)
    }
    return mutable
}

private fun createLifeCycle(): LifecycleRegistry {
    val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
    lifecycle.handleLifecycleEvent(ON_RESUME)
    return  lifecycle
}