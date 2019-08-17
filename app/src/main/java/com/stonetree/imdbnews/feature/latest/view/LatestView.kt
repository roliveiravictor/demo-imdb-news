package com.stonetree.imdbnews.feature.latest.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.facebook.drawee.backends.pipeline.Fresco
import com.stonetree.corerepository.feature.repository.CoreRepository
import com.stonetree.imdbnews.core.utils.InjectorUtils
import com.stonetree.imdbnews.databinding.ViewLatestBinding
import com.stonetree.imdbnews.feature.latest.view.adapter.LatestAdapter
import com.stonetree.imdbnews.feature.latest.viewmodel.LatestViewModel
import android.content.Context
import java.lang.reflect.Modifier.PRIVATE

class LatestView : Fragment() {

    @VisibleForTesting(otherwise = PRIVATE)
    val vm: LatestViewModel by viewModels {
        InjectorUtils.provideLatestViewModelFactory()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Fresco.initialize(context)
        CoreRepository.start(context)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              viewGroup: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val data = ViewLatestBinding.inflate(inflater, viewGroup, false)
        val adapter = LatestAdapter()

        bindXml(data, adapter)
        bindObservers(data, adapter)
        bindObservers(data, adapter)

        return data.root
    }

    private fun bindXml(
        data: ViewLatestBinding,
        adapter: LatestAdapter
    ) {
        data.view = this@LatestView
        data.latest.adapter = adapter
    }

    private fun bindObservers(
        data: ViewLatestBinding,
        adapter: LatestAdapter
    ){
        vm.latest.observe(viewLifecycleOwner) { latest ->
            adapter.submitList(latest)
        }

        vm.network.observe(viewLifecycleOwner) { network ->
            data.network = network
        }
    }
}
