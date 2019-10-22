package com.stonetree.restclient.core.model

import android.view.View

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val isLoading: Int,
    val msg: String? = null
) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS, View.GONE)
        val LOADING = NetworkState(Status.RUNNING, View.VISIBLE)
        fun error(msg: String?) = NetworkState(Status.FAILED, View.GONE, msg)
    }
}
