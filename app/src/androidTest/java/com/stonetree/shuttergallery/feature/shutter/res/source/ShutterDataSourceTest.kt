package com.stonetree.shuttergallery.feature.shutter.res.source

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.corerepository.feature.repository.CoreRepository
import junit.framework.TestCase.assertNull
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShutterDataSourceTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val repository = CoreRepository.start(context)

    private val source = ShutterDataSource()

    @Test
    fun test_shutterDataSource_shouldReturnDefaultValues() {
        assertThat(source.getNetwork(),`is`(any(MutableLiveData::class.java)))
        assertNull(source.getNetwork().value)
    }
}