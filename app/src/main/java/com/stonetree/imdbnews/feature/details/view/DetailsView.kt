package com.stonetree.imdbnews.feature.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.stonetree.imdbnews.core.utils.InjectorUtils
import com.stonetree.imdbnews.databinding.ViewDetailsBinding
import com.stonetree.imdbnews.feature.details.viewmodel.DetailsViewModel
import java.lang.reflect.Modifier

class DetailsView : Fragment() {

    private val args: DetailsViewArgs by navArgs()

    @VisibleForTesting(otherwise = Modifier.PRIVATE)
    val vm: DetailsViewModel by viewModels {
        InjectorUtils.provideDetailsViewModelFactory(args)
    }

    override fun onCreateView
                (inflater: LayoutInflater, viewGroup: ViewGroup?, savedInstanceState: Bundle?): View? {

        val data = ViewDetailsBinding.inflate(inflater, viewGroup, false)

        bindXml(data)
        bindObservers(data)
        bindObservers(data)

        return data.root
    }

    private fun bindXml(data: ViewDetailsBinding) {
        data.view = this@DetailsView
    }

    private fun bindObservers(data: ViewDetailsBinding) {
        vm.details.observe(viewLifecycleOwner) { details ->
            data.url = details.poster
            data.title.text = details.title
            data.description.text = details.description
        }

        vm.network.observe(viewLifecycleOwner) { network ->
            data.network = network
        }
    }
}
