package com.stonetree.view.core

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("isLoading")
fun bindIsLoading(view: View, isLoading: Int?) {
    when (isLoading) {
        -1 -> view.visibility = View.GONE
        0 -> view.visibility = View.VISIBLE
        1 -> view.visibility = View.GONE
    }
}
@BindingAdapter("isLoaded")
fun bindIsLoaded(view: View, isLoaded: Int?) {
    when (isLoaded) {
        -1 -> view.visibility = View.GONE
        0 -> view.visibility = View.GONE
        1 -> view.visibility = View.VISIBLE
    }
}

@BindingAdapter("isFailure")
fun bindIsFailure(view: View, isFailure: Int?) {
    when (isFailure) {
        -1 -> view.visibility = View.VISIBLE
        0 -> view.visibility = View.GONE
        1 -> view.visibility = View.GONE
    }
}