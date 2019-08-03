package com.stonetree.shuttergallery.feature.shutter.view.adapter

import com.stonetree.shuttergallery.core.BaseViewHolder
import com.stonetree.shuttergallery.databinding.ListItemShutterBinding

class ShutterViewHolder(private val bind: ListItemShutterBinding)
    : BaseViewHolder<String>(bind)
{
    override fun onBind(data: String) {
        bind.url = data
    }
}
