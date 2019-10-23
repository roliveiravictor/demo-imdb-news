package com.stonetree.restclient.feature.httpclient

import com.stonetree.restclient.core.constants.RestclientConstants
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class CoreHttpClientImpl(private val interceptor: RestClientInterceptor) : CoreHttpClient{

    override fun create(): OkHttpClient {
            return OkHttpClient.Builder()
            .connectTimeout(RestclientConstants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(RestclientConstants.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(RestclientConstants.TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(interceptor.log())
            .build()
    }
}