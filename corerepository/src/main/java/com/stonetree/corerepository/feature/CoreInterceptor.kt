package com.stonetree.corerepository.feature

import com.stonetree.corerepository.extensions.read
import okhttp3.Credentials
import okhttp3.Interceptor.Chain
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.util.*

abstract class CoreInterceptor {

    companion object {

        private val credentials: String = Credentials.basic(
            Properties().read(RepositoryConstants.USERNAME),
            Properties().read(RepositoryConstants.PASSWORD)
        )

        fun getAuthentication(chain: Chain): Response {
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