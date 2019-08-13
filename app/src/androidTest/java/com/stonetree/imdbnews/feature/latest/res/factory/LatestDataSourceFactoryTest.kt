package com.stonetree.imdbnews.feature.latest.res.factory

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.corerepository.feature.repository.CoreRepository
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LatestDataSourceFactoryTest {

    private lateinit var factory: LatestDataSourceFactory

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val repository = CoreRepository.start(context)

    @Before
    fun setup() {
        factory = LatestDataSourceFactory(repository)
    }

    @Test
    fun test_latestDataSourceFactory_shouldReturnDefaultValues() {
        assertThat(factory.data,`is`(any(MutableLiveData::class.java)))
        assertNull(factory.source)
    }

    @Test
    fun test_create_shouldReturnFactoryFilled() {
        factory.create()
        assertNotNull(factory.source)
    }
}