package com.stonetree.imdbnews.feature.latest.res.factory

import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.stonetree.corerepository.feature.idling.CoreRepositoryIdling
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
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

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    @Before
    fun setup() {
        repository = LatestRepository.getInstance()
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