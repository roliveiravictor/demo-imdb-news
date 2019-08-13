package com.stonetree.imdbnews.feature.latest.view.adapter

import android.view.View
import androidx.navigation.findNavController
import com.stonetree.imdbnews.core.viewholder.BaseViewHolder
import com.stonetree.imdbnews.databinding.ListItemLatestBinding
import com.stonetree.imdbnews.feature.latest.model.Movie
import com.stonetree.imdbnews.feature.latest.res.directions.LatestDirections

class LatestViewHolder(private val bind: ListItemLatestBinding)
    : BaseViewHolder<Movie>(bind)
{
    override fun onBind(data: Movie) {
        data.poster?.let {poster ->
            bind.url = poster
            bind.listener = createOnClickListener(data.id)
        }
    }

    private fun createOnClickListener(id: Long?): View.OnClickListener? {
        return id?.let {
            return@let View.OnClickListener { view ->
                val direction = LatestDirections.actionLatestToDetails(id)
                view.findNavController().navigate(direction)
            }
        }
    }
}
