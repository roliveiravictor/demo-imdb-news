package com.stonetree.shuttergallery.feature.shutter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.stonetree.corerepository.feature.RepositoryConstants.FETCH_DISTANCE
import com.stonetree.corerepository.feature.RepositoryConstants.PAGE_SIZE
import com.stonetree.shuttergallery.feature.shutter.model.Image
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel
import com.stonetree.shuttergallery.feature.shutter.res.factory.ShutterDataSourceFactory
import com.stonetree.shuttergallery.feature.shutter.res.repository.ShutterRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ShutterViewModel : ViewModel() {

    private val repository = ShutterRepository.getInstance()

    private val factory: ShutterDataSourceFactory = ShutterDataSourceFactory(repository)

    private val config: PagedList.Config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(FETCH_DISTANCE)
        .setEnablePlaceholders(false)
        .build()

    val shutters: LiveData<PagedList<Image>> = LivePagedListBuilder(factory, config).build()

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    init {
        viewModelScope.launch {
            repository.fetch()
        }
    }

}