package com.stonetree.corerepository.feature.repository

import android.content.Context
import com.stonetree.corerepository.core.extensions.read
import com.stonetree.corerepository.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.corerepository.core.constants.RepositoryConstants.API_KEY
import com.stonetree.corerepository.core.constants.RepositoryConstants.REPOSITORY_PROPS
import com.stonetree.corerepository.core.constants.RepositoryConstants.TIMEOUT
import com.stonetree.corerepository.feature.httpclient.CoreHttpClient
import com.stonetree.corerepository.feature.interceptor.CoreInterceptor
import com.stonetree.corerepository.feature.interceptor.CoreInterceptorImpl
import okhttp3.OkHttpClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Constructor
import java.util.concurrent.TimeUnit.SECONDS
import kotlin.reflect.KClass

class CoreRepositoryImpl : KoinComponent, CoreRepository {

    private lateinit var retrofit: Retrofit

    private var baseUrl: String = ""

    private var key: String = ""

    private val httpClient: CoreHttpClient by inject()

    override fun key(): String = key

    override fun <T : Any> create(clazz: KClass<T>): T {
        return this.retrofit.create(clazz.java)
    }

    override fun start(context: Context) {
        baseUrl = REPOSITORY_PROPS.read(context, BASE_URL)
        key = REPOSITORY_PROPS.read(context, API_KEY)
        retrofit = configureRetrofit()
    }

    private fun configureRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.create())
            .build()
    }
}
