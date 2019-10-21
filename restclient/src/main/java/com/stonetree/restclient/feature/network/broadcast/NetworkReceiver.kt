package com.stonetree.restclient.feature.network.broadcast

import android.content.BroadcastReceiver
import android.content.Context


interface NetworkReceiver {

    fun get(): BroadcastReceiver

    fun onConnectivityChange(context: Context)
}