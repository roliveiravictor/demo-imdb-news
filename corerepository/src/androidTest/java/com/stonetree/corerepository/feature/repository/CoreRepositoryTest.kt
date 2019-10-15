package com.stonetree.corerepository.feature.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoreRepositoryTest {

    private val context = getInstrumentation().targetContext

    private val repository = CoreRepositoryImpl.getInstance()

    @Before
    fun setup() {
        CoreRepositoryImpl.start(context)
    }

    @Test
    fun test_httpClient_shouldReturnDefaultValues() {
        repository.httpClient.apply {
            assertEquals(5000, readTimeoutMillis)
            assertEquals(5000, writeTimeoutMillis)
            assertEquals(5000, connectTimeoutMillis)
            assertEquals(0, callTimeoutMillis)
            assertEquals(0, networkInterceptors.size)
            assertEquals(1, interceptors.size)
            assertEquals(2, protocols.size)
        }
    }

    @Test
    fun test_baseUrl_shouldReturnNotEmpty() {
        assertFalse(repository.retrofit.baseUrl().toString().isEmpty())
    }

    @Test
    fun test_apiKey_shouldReturnNotEmpty() {
        assertFalse(repository.apiKey.isEmpty())
    }
}