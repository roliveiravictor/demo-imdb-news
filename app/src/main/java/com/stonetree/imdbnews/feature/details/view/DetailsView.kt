package com.stonetree.imdbnews.feature.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.stonetree.imdbnews.databinding.ViewDetailsBinding
import com.stonetree.imdbnews.feature.details.res.repository.DetailsRepository
import com.stonetree.imdbnews.feature.details.viewmodel.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import java.lang.reflect.Modifier

class DetailsView : Fragment() {

    private val args: DetailsViewArgs by navArgs()

    val vm: DetailsViewModel by viewModel { parametersOf(args) }

    private val module = module {
        factory { DetailsRepository(get()) }
        viewModel { (args: DetailsViewArgs) -> DetailsViewModel(get(), args) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadKoinModules(module)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewDetailsBinding
            .inflate(inflater, viewGroup, false)

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
