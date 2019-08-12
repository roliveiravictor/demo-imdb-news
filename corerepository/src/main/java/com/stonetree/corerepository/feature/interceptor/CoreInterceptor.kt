package com.stonetree.corerepository.feature.interceptor

import com.stonetree.corerepository.core.constants.RepositoryConstants.API_KEY
import okhttp3.Interceptor.Chain
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY

abstract class CoreInterceptor {

    companion object {

        fun getAuthentication(chain: Chain, apiKey: String): Response {
            val request = chain.request()

            val authenticatedRequest = request.newBuilder()
                .header(API_KEY, apiKey)
                .build()

            return chain.proceed(authenticatedRequest)
        }

        fun getLogging(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = BODY
            return logging
        }
    }
}
