package com.stonetree.imdbnews.feature.latest.res.factory

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
import com.stonetree.imdbnews.feature.latest.res.source.LatestDataSource
import com.stonetree.restclient.feature.repository.RestClient
import com.stonetree.restclient.feature.repository.RestClientImpl
import junit.framework.TestCase.assertNull
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

    private lateinit var factory: LatestDataSourceFactory

    private lateinit var repository: LatestRepository

    private val client = RestClientImpl()

    private val source = LatestDataSource(repository)

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    @Before
    fun setup() {
        repository = LatestRepository(client)
        factory = LatestDataSourceFactory(repository, source)
    }

    @Test
    fun test_latestDataSourceFactory_shouldReturnDefaultValues() {
        assertThat(factory.data, `is`(any(MutableLiveData::class.java)))
        assertNull(factory.source)
    }

    @Test
    fun test_create_shouldReturnFactoryFilled() {
        factory.create()
        assertNotNull(factory.source)
    }
}