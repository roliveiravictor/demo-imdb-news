package com.stonetree.corerepository.feature.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

class CoreInterceptorImpl : CoreInterceptor {

    override fun log(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = BODY
        return logging
    }
}
