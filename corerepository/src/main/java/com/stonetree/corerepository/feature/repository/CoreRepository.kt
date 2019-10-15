package com.stonetree.corerepository.feature.repository

import android.content.Context
import kotlin.reflect.KClass

interface CoreRepository {

    fun start(context: Context)

    fun<T : Any> create(clazz: KClass<T>) : T

    fun key(): String
}