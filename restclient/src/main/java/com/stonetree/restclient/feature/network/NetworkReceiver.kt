package com.stonetree.restclient.feature.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


interface NetworkReceiver {

    fun get(): BroadcastReceiver

    fun offlineMessageKey(): String

    fun registerOfflineIntent(action: String, message: String)

    fun registerOnlineIntent(action: String)
}