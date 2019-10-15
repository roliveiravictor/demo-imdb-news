package com.stonetree.imdbnews.feature.latest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stonetree.corerepository.core.constants.RepositoryConstants.PRE_FETCH_DISTANCE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.imdbnews.feature.latest.res.factory.LatestDataSourceFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.lifecycle.Transformations.switchMap
import com.stonetree.corerepository.core.constants.RepositoryConstants.MAX_THREADS
import java.util.concurrent.Executors
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.imdbnews.feature.latest.model.Movie
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository

class LatestViewModel(
    val repository: LatestRepository,
    val source: LatestDataSourceFactory
) : ViewModel() {

    val config: PagedList.Config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PRE_FETCH_DISTANCE)
        .setEnablePlaceholders(false)
        .build()

    val latest: LiveData<PagedList<Movie>> =
        LivePagedListBuilder(source, config)
        .setFetchExecutor(Executors.newFixedThreadPool(MAX_THREADS))
        .build()

    val network: LiveData<NetworkState> = switchMap(source.data) {
        data -> data.getNetwork()
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
