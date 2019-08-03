package com.stonetree.shuttergallery.feature.shutter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.stonetree.corerepository.feature.CoreRepository
import com.stonetree.shuttergallery.R
import com.stonetree.shuttergallery.databinding.ViewShutterBinding
import com.stonetree.shuttergallery.feature.shutter.view.adapter.ShutterAdapter
import com.stonetree.shuttergallery.feature.shutter.viewmodel.ShutterViewModel

class ShutterView : AppCompatActivity() {

    private val vm by lazy {
        return@lazy ViewModelProviders.of(this).get(ShutterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoreRepository.start(this)

        val data: ViewShutterBinding = DataBindingUtil.setContentView(
            this, R.layout.view_shutter
        )
        val adapter = ShutterAdapter()

        bindXml(data, adapter)
        bindObservers(data, adapter)
    }

    private fun bindXml(
        data: ViewShutterBinding,
        adapter: ShutterAdapter
    ) {
        data.view = this@ShutterView
        data.shutters.adapter = adapter
    }

    private fun bindObservers(
        data: ViewShutterBinding,
        adapter: ShutterAdapter
    ){
        vm.shutters.observe(this) { shutters ->
            adapter.submitList(shutters)
        }

        vm.isLoading.observe(this) { isLoading ->
            data.isLoading = isLoading
        }
    }
}
