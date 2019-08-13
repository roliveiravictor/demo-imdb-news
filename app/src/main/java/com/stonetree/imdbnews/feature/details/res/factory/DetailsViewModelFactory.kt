package com.stonetree.imdbnews.feature.details.res.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stonetree.imdbnews.feature.details.view.DetailsViewArgs
import com.stonetree.imdbnews.feature.details.viewmodel.DetailsViewModel

class DetailsViewModelFactory(private val args: DetailsViewArgs) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(args) as T
    }
}