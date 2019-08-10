package com.stonetree.shuttergallery.feature.shutter.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.*
import androidx.paging.PagedListAdapter
import com.stonetree.shuttergallery.R
import com.stonetree.shuttergallery.feature.shutter.model.Image

class ShutterAdapter : PagedListAdapter<Image, ShutterViewHolder>(ShutterDiffCallback()) {

    override fun onBindViewHolder(holder: ShutterViewHolder, position: Int) {
        getItem(position)?.let { image ->
            with(holder) {
                itemView.tag = position
                onBind(image.assets.thumb.url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShutterViewHolder {
        return ShutterViewHolder(
            inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_shutter, parent, false
            )
        )
    }
}
