package com.stonetree.imdbnews.feature.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stonetree.imdbnews.core.utils.InjectorUtils
import com.stonetree.imdbnews.databinding.ViewDetailsBinding
import com.stonetree.imdbnews.feature.details.viewmodel.DetailsViewModel
import java.lang.reflect.Modifier

class DetailsView : Fragment() {

    @VisibleForTesting(otherwise = Modifier.PRIVATE)
    val vm: DetailsViewModel by viewModels {
        InjectorUtils.provideDetailsViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              viewGroup: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val container = ViewDetailsBinding.inflate(inflater, viewGroup, false)

        bindXml(container)
        bindObservers(container)
        bindObservers(container)

        return container.root
    }

    private fun bindXml(data: ViewDetailsBinding) {
        data.view = this@DetailsView
    }

    private fun bindObservers(data: ViewDetailsBinding) {

    }
}