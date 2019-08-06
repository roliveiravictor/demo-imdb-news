package com.stonetree.corerepository.core.extensions

import android.content.Context
import com.stonetree.corerepository.feature.idling.CoreRepositoryIdling
import com.stonetree.corerepository.feature.callback.CallBackKt
import retrofit2.Call
import java.util.*

fun<T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
    CoreRepositoryIdling.getResource().increment()
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