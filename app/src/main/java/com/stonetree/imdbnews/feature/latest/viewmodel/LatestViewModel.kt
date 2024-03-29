package com.stonetree.imdbnews.feature.latest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stonetree.restclient.core.constants.RestclientConstants.PRE_FETCH_DISTANCE
import com.stonetree.restclient.core.constants.RestclientConstants.PAGE_SIZE
import com.stonetree.imdbnews.feature.latest.res.factory.LatestDataSourceFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.stonetree.restclient.core.constants.RestclientConstants.MAX_THREADS
import java.util.concurrent.Executors
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.imdbnews.feature.latest.model.Movie
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository

class LatestViewModel(
    val repository: LatestRepository,
    val factory: LatestDataSourceFactory
) : ViewModel() {

    val config: PagedList.Config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PRE_FETCH_DISTANCE)
        .setEnablePlaceholders(false)
        .build()

    val latest: LiveData<PagedList<Movie>> =
        LivePagedListBuilder(factory, config)
            .setFetchExecutor(Executors.newFixedThreadPool(MAX_THREADS))
            .build()

    val network: LiveData<NetworkState> = repository.network

    fun retry() = factory.data.value?.invalidate()

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
