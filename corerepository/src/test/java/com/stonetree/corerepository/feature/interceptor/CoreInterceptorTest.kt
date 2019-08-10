package com.stonetree.corerepository.feature.interceptor

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.junit.Test

class CoreInterceptorTest {

    @Test
    fun test_authenticationInterceptor_shouldReturnNotNull() {
        val credentials: String = Credentials.basic(
            "mUsername",
            "mPassword"
        )

        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                CoreInterceptor.getAuthentication(chain, credentials)
            }.build()

        assertNotNull(httpClient.interceptors.first())
    }

    @Test
    fun test_logging_shouldReturnLevelBody() {
        val logging = CoreInterceptor.getLogging()
        assertEquals(BODY, logging.level)
    }
}
