package com.stonetree.shuttergallery.feature.shutter.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import com.stonetree.shuttergallery.R
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel

class ShutterAdapter : PagedListAdapter<ShutterModel, ShutterViewHolder>(ShutterDiffCallback()) {

    override fun onBindViewHolder(holder: ShutterViewHolder, position: Int) {
        getItem(position)?.let { model ->
            with(holder) {
                itemView.tag = position
                onBind(model)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShutterViewHolder {
        return ShutterViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_shutter, parent, false
            )
        )
    }
}