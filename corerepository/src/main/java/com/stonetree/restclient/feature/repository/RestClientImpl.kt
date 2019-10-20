package com.stonetree.restclient.feature.repository

import android.content.Context
import com.stonetree.restclient.core.extensions.read
import com.stonetree.restclient.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.restclient.core.constants.RepositoryConstants.API_KEY
import com.stonetree.restclient.core.constants.RepositoryConstants.REPOSITORY_PROPS
import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.reflect.KClass

class RestClientImpl(private var baseUrl: String = "") : KoinComponent, RestClient {

    private lateinit var retrofit: Retrofit

    private var key: String = ""

    private val httpClient: CoreHttpClient by inject()

    override fun key(): String = key

    override fun <T : Any> create(clazz: KClass<T>): T {
        return this.retrofit.create(clazz.java)
    }

    override fun start(context: Context) {
        loadBaseUrl(context)
        loadAppKey(context)
        retrofit = configureRetrofit()
    }

    private fun loadBaseUrl(context: Context) {
        if(baseUrl.isEmpty()) baseUrl = REPOSITORY_PROPS.read(context, BASE_URL)
    }

    private fun loadAppKey(context: Context) {
        key = REPOSITORY_PROPS.read(context, API_KEY)
    }

    private fun configureRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.create())
            .build()
    }
}
