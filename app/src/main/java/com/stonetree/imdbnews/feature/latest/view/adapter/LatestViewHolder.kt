package com.stonetree.imdbnews.feature.latest.view.adapter

import com.stonetree.imdbnews.core.viewholder.BaseViewHolder
import com.stonetree.imdbnews.databinding.ListItemLatestBinding

class LatestViewHolder(private val bind: ListItemLatestBinding)
    : BaseViewHolder<String>(bind)
{
    override fun onBind(data: String) {
        bind.url = data
    }
}
