package com.stonetree.restclient.feature.httpclient

import com.stonetree.restclient.core.constants.RepositoryConstants
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class CoreHttpClientImpl(private val interceptor: RestClientInterceptor) : CoreHttpClient{

    override fun create(): OkHttpClient {
            return OkHttpClient.Builder()
            .connectTimeout(RepositoryConstants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RepositoryConstants.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RepositoryConstants.TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor.log())
            .build()
    }
}