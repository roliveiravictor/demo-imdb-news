package com.stonetree.imdbnews.feature.latest.res.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stonetree.imdbnews.feature.latest.viewmodel.LatestViewModel

class LatestViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LatestViewModel() as T
    }
}