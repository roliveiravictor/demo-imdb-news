package com.stonetree.corerepository.feature.httpclient

import okhttp3.OkHttpClient

interface CoreHttpClient {

    fun create(): OkHttpClient
}