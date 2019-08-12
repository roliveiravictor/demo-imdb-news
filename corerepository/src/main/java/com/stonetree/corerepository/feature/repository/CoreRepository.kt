package com.stonetree.corerepository.feature.repository

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.stonetree.corerepository.core.extensions.read
import com.stonetree.corerepository.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.corerepository.core.constants.RepositoryConstants.API_KEY
import com.stonetree.corerepository.core.constants.RepositoryConstants.REPOSITORY_PROPS
import com.stonetree.corerepository.core.constants.RepositoryConstants.TIMEOUT
import com.stonetree.corerepository.feature.interceptor.CoreInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier.PRIVATE
import java.util.concurrent.TimeUnit.SECONDS

class CoreRepository {

    lateinit var retrofit: Retrofit

    private var baseUrl: String = ""
    private var apiKey: String = ""

    @VisibleForTesting(otherwise = PRIVATE)
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, SECONDS)
        .readTimeout(TIMEOUT, SECONDS)
        .writeTimeout(TIMEOUT, SECONDS)
        .addInterceptor(CoreInterceptor.getLogging())
        .addInterceptor { chain ->
            CoreInterceptor.getAuthentication(chain, apiKey)
        }
        .build()

    companion object {

        private var instance: CoreRepository? = null
        fun getInstance() =
            instance
                ?: CoreRepository().also { repository ->
                    instance = repository
                }

        fun start(context: Context) {
            getInstance().apply {
                baseUrl = REPOSITORY_PROPS.read(context, BASE_URL)
                apiKey = REPOSITORY_PROPS.read(context, API_KEY)
            }.run {
                configureRetrofit()
            }
        }

        private fun configureRetrofit() {
            instance?.apply {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
            }
        }
    }
}

