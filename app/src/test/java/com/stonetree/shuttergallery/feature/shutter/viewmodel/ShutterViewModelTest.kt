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
import org.junit.Rule

//TODO - Enhance ShutterViewModel test (i.e. vm.shutters) and abstraction for LiveDataTest class
class ShutterViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var vm: ShutterViewModel

    private lateinit var lifecycle: LifecycleRegistry

    private inline fun<reified T> lambdaMock(): T = mock(T::class.java)

    class LiveDataTest(vm: ShutterViewModel) {

        val network = MutableLiveData(vm.network.value)

        fun observeNetworkChanges(lifecycle: Lifecycle, observer: (NetworkState) -> Unit) {
            network.observe({ lifecycle }) { data ->
                data?.let(observer)
            }
        }
    }

    @Before
    fun setup(){
        vm = ShutterViewModel()
        lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
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
        val test = LiveDataTest(vm)
        test.observeNetworkChanges(lifecycle, observer)
        test.network.postValue(NetworkState.LOADING)
        verify(observer).invoke(NetworkState.LOADING)
    }
}