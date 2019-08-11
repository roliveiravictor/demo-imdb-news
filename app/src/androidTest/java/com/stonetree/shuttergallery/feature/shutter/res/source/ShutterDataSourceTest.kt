package com.stonetree.shuttergallery.feature.shutter.res.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PageKeyedDataSource.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.corerepository.feature.repository.CoreRepository
import com.stonetree.shuttergallery.feature.shutter.model.Image
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShutterDataSourceTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val repository = CoreRepository.start(context)

    private lateinit var source: ShutterDataSource

    private class InitialCallback : LoadInitialCallback<Long, Image>() {
        override fun onResult(
            data: MutableList<Image>,
            position: Int,
            totalCount: Int,
            previousPageKey: Long?,
            nextPageKey: Long?
        ) {

        }

        override fun onResult(
            data: MutableList<Image>,
            previousPageKey: Long?,
            nextPageKey: Long?
        ) {

        }
    }

    private class LoadCallback : PageKeyedDataSource.LoadCallback<Long, Image>() {
        override fun onResult(data: MutableList<Image>, adjacentPageKey: Long?) {

        }
    }

    @Before
    fun setup() {
        source = ShutterDataSource()
    }

    @Test
    fun test_shutterDataSource_shouldReturnDefaultValues() {
        assertThat(source.getNetwork(),`is`(any(MutableLiveData::class.java)))
        assertNull(source.getNetwork().value)
    }

    @Test
    fun test_getNextKey_shouldReturnTwo() {
        val currentKey = LoadParams(1L, PAGE_SIZE)
        assertEquals(2L, source.getNextKey(ShutterModel(), currentKey))
    }

    @Test
    fun test_getNextKey_shouldReturnNull() {
        val currentKey = LoadParams(1L, PAGE_SIZE)

        val model = ShutterModel()
        model.totalCount = 1

        assertNull(source.getNextKey(model, currentKey))
    }

    @Test
    fun test_loadInitialNetworkState_shouldReturnLoading() {
        val params = LoadInitialParams<Long>(PAGE_SIZE, false)
        source.loadInitial(params, InitialCallback())
        assertEquals(NetworkState.LOADING, source.getNetwork().value)
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