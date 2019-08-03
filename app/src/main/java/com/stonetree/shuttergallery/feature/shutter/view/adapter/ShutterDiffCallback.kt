package com.stonetree.shuttergallery.feature.shutter.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stonetree.shuttergallery.feature.shutter.model.Image

class ShutterDiffCallback : DiffUtil.ItemCallback<Image>() {

    override fun areItemsTheSame(
        oldItem: Image,
        newItem: Image
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Image,
        newItem: Image
    ): Boolean {
        return oldItem.assets.thumb.url == newItem.assets.thumb.url
    }
}