package com.stonetree.shuttergallery.binders

import android.view.View
import androidx.databinding.BindingAdapter
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("loadImage")
fun bindLoadImage(view: SimpleDraweeView, imageUrl: String) {
    val controller = Fresco.newDraweeControllerBuilder()
        .setUri(imageUrl)
        .build()
    view.controller = controller
}