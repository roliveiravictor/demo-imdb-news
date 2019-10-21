package com.stonetree.restclient.feature.httpclient

import okhttp3.OkHttpClient

interface CoreHttpClient {

    fun create(): OkHttpClient
}