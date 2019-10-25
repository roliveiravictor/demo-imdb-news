package com.stonetree.imdbnews.feature.details.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.stonetree.imdbnews.databinding.ViewDetailsBinding
import com.stonetree.imdbnews.feature.details.viewmodel.DetailsViewModel
import com.stonetree.view.feature.fragment.CoreFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsView : CoreFragment() {

    private val args: DetailsViewArgs by navArgs()

    val vm: DetailsViewModel by viewModel { parametersOf(args) }

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewDetailsBinding
            .inflate(inflater, viewGroup, false)

        bindXml(data)
        bindObservers(data)

        return data.root
    }

    override fun onRequestRetry() {
        Log.e(javaClass.name, "Details Retry")
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
