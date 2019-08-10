package com.stonetree.shuttergallery.feature.shutter.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.*
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stonetree.corerepository.core.constants.RepositoryConstants.PRE_FETCH_DISTANCE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.shuttergallery.feature.shutter.model.Image
import com.stonetree.shuttergallery.feature.shutter.res.factory.ShutterDataSourceFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import androidx.lifecycle.Transformations.switchMap
import com.stonetree.corerepository.core.constants.RepositoryConstants.MAX_THREADS
import java.util.concurrent.Executors
import com.stonetree.corerepository.core.model.NetworkState

class ShutterViewModel : ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val factory: ShutterDataSourceFactory = ShutterDataSourceFactory()

    @VisibleForTesting(otherwise = PRIVATE)
    val config: PagedList.Config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PRE_FETCH_DISTANCE)
        .setEnablePlaceholders(false)
        .build()

    val shutters: LiveData<PagedList<Image>> =
        LivePagedListBuilder(factory, config)
        .setFetchExecutor(Executors.newFixedThreadPool(MAX_THREADS))
        .build()

    val network: LiveData<NetworkState> = switchMap(factory.data) {
        data -> data.getNetwork()
    }

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
