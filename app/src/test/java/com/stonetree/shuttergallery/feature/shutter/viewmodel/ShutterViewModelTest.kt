package com.stonetree.shuttergallery.feature.shutter.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.shuttergallery.feature.shutter.res.factory.ShutterDataSourceFactory
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.stonetree.corerepository.core.constants.RepositoryConstants.PRE_FETCH_DISTANCE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.shuttergallery.core.livedata.lambdaMock
import com.stonetree.shuttergallery.core.livedata.observeLiveData
import com.stonetree.shuttergallery.feature.shutter.model.Image
import com.stonetree.shuttergallery.feature.shutter.res.source.ShutterDataSource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import org.junit.Rule

class ShutterViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var vm: ShutterViewModel

    @Before
    fun setup(){
        vm = ShutterViewModel()
    }

    @Test
    fun test_shutterViewModel_shouldReturnDefaultValues() {
        vm.apply {
            assertThat(factory,`is`(any(ShutterDataSourceFactory::class.java)))
            assertThat(config,`is`(any(PagedList.Config::class.java)))
            assertThat(shutters,`is`(any(LiveData::class.java)))
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
        val observer = lambdaMock<(PagedList<Image>) -> Unit>()
        val mutableData = vm.observeLiveData("shutters", observer)

        val paged = mock(PagedList::class.java)
        mutableData.postValue(paged as PagedList<Image>?)
        verify(observer).invoke(paged as PagedList<Image>)
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
