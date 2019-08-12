package com.stonetree.imdbnews.feature.latest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.facebook.drawee.backends.pipeline.Fresco
import com.stonetree.corerepository.feature.repository.CoreRepository
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.databinding.ViewLatestBinding
import com.stonetree.imdbnews.feature.latest.view.adapter.LatestAdapter
import com.stonetree.imdbnews.feature.latest.viewmodel.LatestViewModel

class LatestView : AppCompatActivity() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val vm by lazy {
        return@lazy ViewModelProviders.of(this).get(LatestViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Fresco.initialize(this)
        CoreRepository.start(this)

        val data: ViewLatestBinding = DataBindingUtil.setContentView(
            this, R.layout.view_latest
        )

        val adapter = LatestAdapter()

        bindXml(data, adapter)
        bindObservers(data, adapter)
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
        vm.latest.observe(this) { latest ->
            adapter.submitList(latest)
        }

        vm.network.observe(this) { network ->
            data.network = network
        }
    }
}
