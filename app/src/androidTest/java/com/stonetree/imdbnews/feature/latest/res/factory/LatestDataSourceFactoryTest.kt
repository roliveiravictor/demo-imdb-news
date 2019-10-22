package com.stonetree.imdbnews.feature.latest.res.factory

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.stonetree.imdbnews.MainActivity
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
import com.stonetree.imdbnews.feature.latest.res.source.LatestDataSource
import com.stonetree.restclient.feature.repository.RestClientImpl
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LatestDataSourceFactoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var factory: LatestDataSourceFactory

    private lateinit var repository: LatestRepository

    private val client = RestClientImpl()

    private lateinit var source: LatestDataSource

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        client.start(context)
        repository = LatestRepository(client)
        source = LatestDataSource(repository)
        factory = LatestDataSourceFactory(repository, source)
    }

    @Test
    fun test_latestDataSourceFactory_shouldReturnDefaultValues() {
        assertThat(factory.data, `is`(any(MutableLiveData::class.java)))
        assertNotNull(factory.source)
    }

    @Test
    fun test_create_shouldReturnFactoryFilled() {
        factory.create()
        assertNotNull(factory.source)
    }
}