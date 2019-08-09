package com.stonetree.corerepository.core.feature

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.*
import com.stonetree.corerepository.feature.repository.CoreRepository
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoreRepositoryTest {

    private val context = getInstrumentation().targetContext

    private val repository =  CoreRepository.getInstance()

    @Before
    fun setup() {
        CoreRepository.start(context)
    }

    @Test
    fun test_httpClient_shouldReturnDefaultValues() {
        repository.httpClient.apply {
            assertEquals(5000, readTimeoutMillis)
            assertEquals(5000, writeTimeoutMillis)
            assertEquals(5000, connectTimeoutMillis)
            assertEquals(0, callTimeoutMillis)
            assertEquals(0, networkInterceptors.size)
            assertEquals(2, interceptors.size)
            assertEquals(2, protocols.size)
        }
    }

    @Test
    fun test_baseUrl_shouldReturnNotEmpty() {
        assertFalse(repository.retrofit.baseUrl().toString().isEmpty())
    }
}