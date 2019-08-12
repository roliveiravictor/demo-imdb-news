package com.stonetree.imdbnews.core.binders

import android.net.Uri
import android.view.View
import androidx.databinding.BindingAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.corerepository.core.model.NetworkState.Companion.LOADING
import com.facebook.imagepipeline.request.ImageRequestBuilder
import com.facebook.imagepipeline.request.ImageRequest

@BindingAdapter("isGone")
fun bindIsGone(view: View, network: NetworkState?) {
    when(network){
        LOADING -> view.visibility = View.VISIBLE
        else -> view.visibility = View.GONE
    }
}

@BindingAdapter("loadImage")
fun bindLoadImage(view: SimpleDraweeView, imageUrl: String) {
    val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
        .setLowestPermittedRequestLevel(ImageRequest.RequestLevel.DISK_CACHE)
        .setProgressiveRenderingEnabled(true)
        .build()

    val controller = Fresco.newDraweeControllerBuilder()
        .setImageRequest(request)
        .setOldController(view.controller)
        .setUri(imageUrl)
        .build()

    view.tag = imageUrl

    view.controller = controller
}
