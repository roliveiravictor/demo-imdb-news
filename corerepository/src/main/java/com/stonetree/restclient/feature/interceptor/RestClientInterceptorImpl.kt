package com.stonetree.restclient.feature.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

class RestClientInterceptorImpl : RestClientInterceptor {

    override fun log(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = BODY
        return logging
    }
}
