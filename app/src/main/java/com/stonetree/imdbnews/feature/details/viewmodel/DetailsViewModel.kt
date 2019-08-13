package com.stonetree.imdbnews.feature.details.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.imdbnews.feature.details.model.DetailsModel
import com.stonetree.imdbnews.feature.details.res.repository.DetailsRepository
import com.stonetree.imdbnews.feature.details.view.DetailsViewArgs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DetailsViewModel(private val args: DetailsViewArgs): ViewModel() {

    private val repository = DetailsRepository.getInstance()

    val network: LiveData<NetworkState> = repository.getNetwork()

    val details: LiveData<DetailsModel> = repository.getDetails()

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    init {
        viewModelScope.launch {
            repository.get(args)
        }
    }
}
