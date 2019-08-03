package com.stonetree.shuttergallery.feature.shutter.view.adapter

import com.stonetree.shuttergallery.core.BaseViewHolder
import com.stonetree.shuttergallery.databinding.ListItemShutterBinding
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel

class ShutterViewHolder(private val bind: ListItemShutterBinding)
    : BaseViewHolder<ShutterModel>(bind)
{
    override fun onBind(model: ShutterModel) {
        bind.url = model.url
    }
}
