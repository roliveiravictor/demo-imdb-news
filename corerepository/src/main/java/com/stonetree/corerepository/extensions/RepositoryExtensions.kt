package com.stonetree.corerepository.extensions

import android.content.Context
import android.util.Log
import com.stonetree.corerepository.BuildConfig
import com.stonetree.corerepository.feature.CallBackKt
import retrofit2.Call
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.util.*

fun<T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
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