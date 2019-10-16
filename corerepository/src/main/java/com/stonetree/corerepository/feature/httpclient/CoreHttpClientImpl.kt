package com.stonetree.corerepository.feature.httpclient

import com.stonetree.corerepository.core.constants.RepositoryConstants
import com.stonetree.corerepository.feature.interceptor.CoreInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class CoreHttpClientImpl(private val interceptor: CoreInterceptor) : CoreHttpClient{

    override fun create(): OkHttpClient {
            return OkHttpClient.Builder()
            .connectTimeout(RepositoryConstants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RepositoryConstants.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RepositoryConstants.TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor.log())
            .build()
    }
}