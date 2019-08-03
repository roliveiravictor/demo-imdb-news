package com.stonetree.corerepository.extensions

import com.stonetree.corerepository.feature.CallBackKt
import com.stonetree.corerepository.feature.CoreRepositoryConstant
import retrofit2.Call
import java.util.*

fun<T> Call<T>.enqueue(callback: CallBackKt<T>.() -> Unit) {
    val callBackKt = CallBackKt<T>()
    callback.invoke(callBackKt)
    this.enqueue(callBackKt)
}

fun Properties.read(name: String): String {
    javaClass.getResourceAsStream(CoreRepositoryConstant.FILE_NAME).use {
            `is` -> load(`is`)?.let {
        return getProperty(name)
        }
    }
}