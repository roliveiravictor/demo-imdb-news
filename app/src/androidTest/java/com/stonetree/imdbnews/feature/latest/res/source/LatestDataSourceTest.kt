package com.stonetree.imdbnews.feature.latest.res.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PageKeyedDataSource.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import com.stonetree.imdbnews.feature.latest.model.Movie
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LatestDataSourceTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val repository = LatestRepository.getInstance()

    private lateinit var source: LatestDataSource

    private class InitialCallback : LoadInitialCallback<Long, Movie>() {
        override fun onResult(
            data: MutableList<Movie>,
            position: Int,
            totalCount: Int,
            previousPageKey: Long?,
            nextPageKey: Long?
        ) {

        }

        override fun onResult(
            data: MutableList<Movie>,
            previousPageKey: Long?,
            nextPageKey: Long?
        ) {

        }
    }

    private class LoadCallback : PageKeyedDataSource.LoadCallback<Long, Movie>() {
        override fun onResult(data: MutableList<Movie>, adjacentPageKey: Long?) {

        }
    }

    @Before
    fun setup() {
        source = LatestDataSource(repository)
    }

    @Test
    fun test_latestDataSource_shouldReturnDefaultValues() {
        assertThat(source.getNetwork(),`is`(any(MutableLiveData::class.java)))
    }

    @Test
    fun test_getNextKey_shouldReturnTwo() {
        val currentKey = LoadParams(1L, PAGE_SIZE)
        assertEquals(2L, source.getNextKey(LatestModel(), currentKey))
    }

    @Test
    fun test_getNextKey_shouldReturnNull() {
        val currentKey = LoadParams(1L, PAGE_SIZE)

        val model = LatestModel()
        model.totalPages = 1

        assertNull(source.getNextKey(model, currentKey))
    }

    @Test
    fun test_loadAfterNetworkState_shouldReturnNull() {
        val params = LoadParams(1L, PAGE_SIZE)
        source.loadAfter(params, LoadCallback())
        assertNull(source.getNetwork().value)
    }

    @Test
    fun test_loadBeforeNetworkState_shouldReturnNull() {
        val params = LoadParams(1L, PAGE_SIZE)
        source.loadBefore(params, LoadCallback())
        assertNull(source.getNetwork().value)
    }
}