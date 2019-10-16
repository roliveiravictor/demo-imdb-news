package com.stonetree.restclient.feature.repository

import android.content.Context
import kotlin.reflect.KClass

interface RestClient {

    fun start(context: Context)

    fun<T : Any> create(clazz: KClass<T>) : T

    fun key(): String
}