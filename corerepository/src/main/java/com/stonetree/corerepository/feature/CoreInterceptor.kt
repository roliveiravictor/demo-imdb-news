package com.stonetree.corerepository.feature

import okhttp3.Interceptor.Chain
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

abstract class CoreInterceptor {

    companion object {

        fun getAuthentication(chain: Chain, credentials: String): Response {
            val request = chain.request()

            val authenticatedRequest = request.newBuilder()
                .header("Authorization", credentials)
                .build()

            return chain.proceed(authenticatedRequest)
        }

        fun getLogging(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }
    }
}