package com.stonetree.restclient.core.model

import android.view.View

enum class Status(val isLoading: Int) {
    RUNNING(0),
    SUCCESS(1),
    FAILED(-1)
}

@Suppress("DataClassPrivateConstructor")
data class NetworkState private constructor(
    val status: Status,
    val msg: String? = null
) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        fun error(msg: String?) = NetworkState(Status.FAILED, msg)
    }
}
