package com.stonetree.restclient.feature.interceptor

import okhttp3.logging.HttpLoggingInterceptor

interface RestClientInterceptor {

    fun log(): HttpLoggingInterceptor
}
