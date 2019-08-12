package com.stonetree.imdbnews.feature.latest.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stonetree.imdbnews.feature.latest.model.Image

class LatestDiffCallback : DiffUtil.ItemCallback<Image>() {

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
