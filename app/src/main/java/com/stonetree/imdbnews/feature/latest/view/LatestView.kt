package com.stonetree.imdbnews.feature.latest.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import com.stonetree.imdbnews.databinding.ViewLatestBinding
import com.stonetree.imdbnews.feature.latest.view.adapter.LatestAdapter
import com.stonetree.imdbnews.feature.latest.viewmodel.LatestViewModel
import com.stonetree.view.feature.fragment.CoreFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LatestView : CoreFragment() {

    private val adapter: LatestAdapter by inject()

    private val vm: LatestViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewLatestBinding.inflate(inflater, viewGroup, false)

        bindXml(data, adapter)
        bindObservers(data, adapter)

        return data.root
    }

    override fun onRequestRetry() {
        Log.e(javaClass.name, "Latest Retry")
    }

    private fun bindXml(
        data: ViewLatestBinding,
        adapter: LatestAdapter
    ) {
        data.view = this@LatestView
        data.latest.adapter = adapter
    }

    private fun bindObservers(data: ViewLatestBinding, adapter: LatestAdapter) {
        vm.latest.observe(viewLifecycleOwner) { latest ->
            adapter.submitList(latest)
        }

        vm.network.observe(viewLifecycleOwner) { network ->
            data.network = network
        }
    }
}
