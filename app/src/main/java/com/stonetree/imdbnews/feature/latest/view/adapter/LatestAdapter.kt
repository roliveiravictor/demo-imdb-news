package com.stonetree.imdbnews.feature.latest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.paging.PagedListAdapter
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.feature.latest.model.Movie

class LatestAdapter : PagedListAdapter<Movie, LatestViewHolder>(
    LatestDiffCallback()
) {

    override fun onBindViewHolder(holder: LatestViewHolder, position: Int) {
        getItem(position)?.let { movie ->
            with(holder) {
                itemView.tag = position
                movie.poster?.let { poster ->
                    onBind(poster)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestViewHolder {
        return LatestViewHolder(
            inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_latest, parent, false
            )
        )
    }
}
