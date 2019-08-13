package com.stonetree.corerepository.feature.interceptor

import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

abstract class CoreInterceptor {

    companion object {

        fun getLogging(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = BODY
            return logging
        }
    }
}
