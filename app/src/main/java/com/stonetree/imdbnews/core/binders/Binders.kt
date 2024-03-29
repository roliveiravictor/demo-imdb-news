package com.stonetree.imdbnews.core.binders

import android.view.View
import androidx.databinding.BindingAdapter
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.core.constants.RestclientConstants.POSTER_URL
import com.stonetree.restclient.core.constants.RestclientConstants.REPOSITORY_PROPS
import com.stonetree.restclient.core.extensions.read
import com.stonetree.restclient.core.model.Status.SUCCESS
import com.stonetree.restclient.core.model.Status.FAILED
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.stonetree.imdbnews.R

@BindingAdapter("isIdle")
fun bindIsIdle(view: View, network: NetworkState?) {
    when (network?.status) {
        SUCCESS -> view.visibility = View.VISIBLE
        FAILED -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("loadImage")
fun bindLoadImage(view: ImageView, posterPath: String? = "") {
    val url = REPOSITORY_PROPS.read(view.context, POSTER_URL) + posterPath

    view.tag = url

    Glide.with(view)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.loading_animation)
        .into(view)
}
