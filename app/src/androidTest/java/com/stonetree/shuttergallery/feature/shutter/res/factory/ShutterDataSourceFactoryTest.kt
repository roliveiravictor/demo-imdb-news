package com.stonetree.shuttergallery.feature.shutter.res.factory

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.corerepository.feature.repository.CoreRepository
import junit.framework.TestCase.*
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShutterDataSourceFactoryTest {

    private lateinit var factory: ShutterDataSourceFactory

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val repository = CoreRepository.start(context)

    @Before
    fun setup() {
        factory = ShutterDataSourceFactory()
    }

    @Test
    fun test_shutterDataSourceFactory_shouldReturnDefaultValues() {
        assertThat(factory.data,`is`(any(MutableLiveData::class.java)))
        assertNull(factory.source)
    }

    @Test
    fun test_create_shouldReturnFactoryFilled() {
        factory.create()
        assertNotNull(factory.source)
    }
}