package com.stonetree.imdbnews.feature.details.res.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stonetree.imdbnews.feature.details.viewmodel.DetailsViewModel
import com.stonetree.imdbnews.feature.latest.viewmodel.LatestViewModel

class DetailsViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel() as T
    }
}