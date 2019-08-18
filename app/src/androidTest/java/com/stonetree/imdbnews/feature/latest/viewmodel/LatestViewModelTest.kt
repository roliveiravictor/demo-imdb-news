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
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.corerepository.core.constants.RepositoryConstants.PRE_FETCH_DISTANCE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.corerepository.feature.repository.CoreRepository
import com.stonetree.imdbnews.core.extensions.lambdaMock
import com.stonetree.imdbnews.core.extensions.observeLiveData
import com.stonetree.imdbnews.feature.latest.model.Movie
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LatestViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val repository = CoreRepository.start(context)

    private lateinit var vm: LatestViewModel

    @Before
    fun setup() {
        vm = LatestViewModel()
    }

    @Test
    fun test_latestViewModel_shouldReturnDefaultValues() {
        vm.apply {
            assertThat(factory, `is`(any(LatestDataSourceFactory::class.java)))
            assertThat(config, `is`(any(PagedList.Config::class.java)))
            assertThat(latest, `is`(any(LiveData::class.java)))
            assertThat(network, `is`(any(LiveData::class.java)))
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
        val observer =
            lambdaMock<(PagedList<Movie>) -> Unit>()
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
