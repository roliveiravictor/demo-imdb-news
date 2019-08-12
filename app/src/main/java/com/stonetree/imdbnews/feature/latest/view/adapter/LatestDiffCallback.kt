package com.stonetree.imdbnews.feature.latest.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.stonetree.imdbnews.feature.latest.model.Movie

class LatestDiffCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean {
        return oldItem.poster == newItem.poster
    }
}
