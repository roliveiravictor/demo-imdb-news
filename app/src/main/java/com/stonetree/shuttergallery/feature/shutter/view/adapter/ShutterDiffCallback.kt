package com.stonetree.shuttergallery.feature.shutter.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel

class ShutterDiffCallback : DiffUtil.ItemCallback<ShutterModel>() {

    override fun areItemsTheSame(
        oldItem: ShutterModel,
        newItem: ShutterModel
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ShutterModel,
        newItem: ShutterModel
    ): Boolean {
        return oldItem.url == newItem.url
    }
}