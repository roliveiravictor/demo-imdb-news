package com.stonetree.corerepository.feature

import com.stonetree.corerepository.extensions.read
import com.stonetree.corerepository.feature.CoreRepositoryConstant.BASE_URL
import com.stonetree.corerepository.feature.CoreRepositoryConstant.TIMEOUT
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

class CoreRepository {

    lateinit var retrofit: Retrofit

    companion object {

        private var instance: CoreRepository? = null
        fun getInstance() =
            instance
                ?: CoreRepository().also {
                    instance = it
                    startRetrofit()
                }

        private fun startRetrofit() {
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(CoreInterceptor.getLogging())
                .addInterceptor { chain ->
                    CoreInterceptor.getAuthentication(chain)
                }
                .build()

            instance?.retrofit = Retrofit.Builder()
                .baseUrl(Properties().read(BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()
        }
    }
}

