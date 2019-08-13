package com.stonetree.imdbnews.feature.latest.viewmodel

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.imdbnews.feature.latest.res.factory.LatestDataSourceFactory
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stonetree.corerepository.core.constants.RepositoryConstants.PRE_FETCH_DISTANCE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.imdbnews.core.livedata.lambdaMock
import com.stonetree.imdbnews.core.livedata.observeLiveData
import com.stonetree.imdbnews.feature.latest.model.Movie
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Rule

class LatestViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var vm: LatestViewModel

    @Before
    fun setup(){
        vm = LatestViewModel()
    }

    @Test
    fun test_latestViewModel_shouldReturnDefaultValues() {
        vm.apply {
            assertThat(factory,`is`(any(LatestDataSourceFactory::class.java)))
            assertThat(config,`is`(any(PagedList.Config::class.java)))
            assertThat(latest,`is`(any(LiveData::class.java)))
            assertThat(network,`is`(any(LiveData::class.java)))
        }
    }


    @Test
    fun test_networkState_shouldReturnChangeLivedData() {
        val observer = lambdaMock<(NetworkState) -> Unit>()
        val mutableData = vm.observeLiveData("network", observer)

        mutableData.postValue(NetworkState.LOADING)
        verify(observer).invoke(NetworkState.LOADING)
    }

    @Test
    fun test_pagedList_shouldReturnChangeLivedData() {
        val observer = lambdaMock<(PagedList<Movie>) -> Unit>()
        val mutableData = vm.observeLiveData("latest", observer)

        val paged = mock(PagedList::class.java)
        mutableData.postValue(paged as PagedList<Movie>?)
        verify(observer).invoke(paged as PagedList<Movie>)
    }

    @Test
    fun test_pageConfig_shouldReturnDefaultConfig() {
        vm.config.apply {
            assertEquals(PAGE_SIZE, initialLoadSizeHint)
            assertEquals(PAGE_SIZE, pageSize)
            assertEquals(PRE_FETCH_DISTANCE, prefetchDistance)
            assertFalse(enablePlaceholders)
        }
    }
}