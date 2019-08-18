package com.stonetree.corerepository.feature.interceptor

import junit.framework.TestCase.assertEquals
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.junit.Test

class CoreInterceptorTest {

    @Test
    fun test_logging_shouldReturnLevelBody() {
        val logging = CoreInterceptor.getLogging()
        assertEquals(BODY, logging.level)
    }
}
