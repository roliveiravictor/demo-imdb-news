package com.stonetree.corerepository.feature.interceptor

import junit.framework.TestCase.assertEquals
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class CoreInterceptorTest: AutoCloseKoinTest() {

    private val module = module {
        factory<CoreInterceptor>{ CoreInterceptorImpl() }
    }

    private val interceptor: CoreInterceptor by inject()

    @Before
    fun setup() {
        startKoin {
            loadKoinModules(module)
        }
    }

    @Test
    fun test_logging_shouldReturnLevelBody() {
        val logging = interceptor.log()
        assertEquals(BODY, logging.level)
    }
}
