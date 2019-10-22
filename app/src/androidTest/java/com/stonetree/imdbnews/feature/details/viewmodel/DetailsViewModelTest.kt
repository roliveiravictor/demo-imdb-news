package com.stonetree.imdbnews.feature.details.viewmodel

import androidx.lifecycle.LiveData
import com.stonetree.restclient.core.model.NetworkState
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.imdbnews.core.extensions.lambdaMock
import com.stonetree.imdbnews.core.extensions.observeLiveData
import com.stonetree.imdbnews.feature.details.model.DetailsModel
import com.stonetree.imdbnews.feature.details.res.repository.DetailsRepository
import com.stonetree.imdbnews.feature.details.view.DetailsViewArgs
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.times

@RunWith(AndroidJUnit4::class)
class DetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val client = RestClientImpl()

    private lateinit var repository: DetailsRepository

    private lateinit var vm: DetailsViewModel

    private val args = mock(DetailsViewArgs::class.java)

    @Before
    fun setup() {
        client.start(context)
        repository = DetailsRepository(client)
        vm = DetailsViewModel(repository, args)
    }

    @Test
    fun test_detailsViewModel_shouldReturnDefaultValues() {
        vm.apply {
            assertThat(network, `is`(any(LiveData::class.java)))
            assertThat(details, `is`(any(LiveData::class.java)))
        }
    }

    @Test
    fun test_networkState_shouldReturnChangeLivedData() {
        val observer = lambdaMock<(NetworkState) -> Unit>()
        val mutableData = vm.observeLiveData("network", observer)

        mutableData.postValue(NetworkState.LOADING)
        verify(observer, times(2)).invoke(NetworkState.LOADING)
    }

    @Test
    fun test_details_shouldReturnChangeLivedData() {
        val observer = lambdaMock<(DetailsModel) -> Unit>()
        val mutableData = vm.observeLiveData("details", observer)

        val details = DetailsModel()
        mutableData.postValue(details)
        verify(observer).invoke(details)
    }
}
