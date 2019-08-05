package com.stonetree.corerepository.extensions

import android.content.Context
import com.stonetree.corerepository.core.idling.EspressoIdlingResource
import com.stonetree.corerepository.feature.res.callback.CallBackKt
import retrofit2.Call
import java.util.*

fun<T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
    EspressoIdlingResource.getIdlingResource().increment()
    val callBackKt = CallBackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}

//TODO - Remove security breach.
fun String.read(context: Context, name: String): String{
    return context.assets.open(this).use {  input ->
        val props = Properties()
        props.load(input)
        props.getProperty(name)
    }
}