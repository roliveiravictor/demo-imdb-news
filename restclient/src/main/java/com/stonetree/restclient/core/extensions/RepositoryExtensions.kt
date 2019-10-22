package com.stonetree.restclient.core.extensions

import android.content.Context
import com.stonetree.restclient.feature.idling.RestClientIdling
import com.stonetree.restclient.feature.RestClientCallback
import retrofit2.Call
import java.util.Properties

fun <T> Call<T>.enqueue(callback: RestClientCallback<T>.() -> Unit) {
    RestClientIdling.getResource().increment()
    val callBackKt = RestClientCallback<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}

// TODO - Remove security breach.
fun String.read(context: Context, name: String): String {
    return context.assets.open(this).use { input ->
        val props = Properties()
        props.load(input)
        props.getProperty(name)
    }
}
