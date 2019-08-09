package com.stonetree.corerepository.feature.repository

import android.content.Context
import androidx.annotation.VisibleForTesting
import com.stonetree.corerepository.core.extensions.read
import com.stonetree.corerepository.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.corerepository.core.constants.RepositoryConstants.PASSWORD
import com.stonetree.corerepository.core.constants.RepositoryConstants.REPOSITORY_PROPS
import com.stonetree.corerepository.core.constants.RepositoryConstants.TIMEOUT
import com.stonetree.corerepository.core.constants.RepositoryConstants.USERNAME
import com.stonetree.corerepository.feature.interceptor.CoreInterceptor
import okhttp3.Credentials
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Modifier.PRIVATE
import java.util.concurrent.TimeUnit.*

class CoreRepository {

    lateinit var retrofit: Retrofit

    private var baseUrl: String = ""
    private var credentials: String = ""

    @VisibleForTesting(otherwise = PRIVATE)
    val httpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, SECONDS)
        .readTimeout(TIMEOUT, SECONDS)
        .writeTimeout(TIMEOUT, SECONDS)
        .addInterceptor(CoreInterceptor.getLogging())
        .addInterceptor { chain ->
            CoreInterceptor.getAuthentication(chain, credentials)
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
                credentials = Credentials.basic(
                    REPOSITORY_PROPS.read(context, USERNAME),
                    REPOSITORY_PROPS.read(context, PASSWORD)
                )
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

