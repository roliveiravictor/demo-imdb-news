package com.stonetree.corerepository.feature.interceptor

import okhttp3.logging.HttpLoggingInterceptor

interface CoreInterceptor {

    fun log(): HttpLoggingInterceptor
}
