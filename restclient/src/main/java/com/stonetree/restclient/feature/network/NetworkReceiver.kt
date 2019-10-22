package com.stonetree.restclient.feature.network

import android.content.BroadcastReceiver
import android.content.Context


interface NetworkReceiver {

    fun get(): BroadcastReceiver

    fun onConnectionOffline()

    fun onConnectionOnline()
}