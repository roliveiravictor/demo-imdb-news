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
        assertThat(vm.factory,`is`(any(ShutterDataSourceFactory::class.java)))
        assertThat(vm.config,`is`(any(PagedList.Config::class.java)))
        assertThat(vm.shutters,`is`(any(LiveData::class.java)))
        assertThat(vm.network,`is`(any(LiveData::class.java)))
    }


    @Test
    fun test_networkState_shouldReturnChangeLivedData() {
        val observer = lambdaMock<(NetworkState) -> Unit>()
        val mutableData = vm.observeLiveData("network", observer)

        mutableData.postValue(NetworkState.LOADING)
        verify(observer).invoke(NetworkState.LOADING)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_pagedList_shouldReturnChangeLivedData() {
        val observer = lambdaMock<(PagedList<Image>) -> Unit>()
        val mutableData = vm.observeLiveData("shutters", observer)

        val list = PagedList.Builder(
            mock(ShutterDataSource::class.java),
            1).build()

        mutableData.postValue(list)
        verify(observer).invoke(list)
    }

    @Test
    fun test_pageConfig_shouldReturnDefaultConfig() {
        assertEquals(PAGE_SIZE, vm.config.initialLoadSizeHint)
        assertEquals(PAGE_SIZE, vm.config.pageSize)
        assertEquals(PRE_FETCH_DISTANCE, vm.config.prefetchDistance)
        assertFalse(vm.config.enablePlaceholders)
    }
}