package com.stonetree.restclient.feature.network

import android.net.ConnectivityManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.*
import android.net.ConnectivityManager.*


class NetworkChangeReceiverImpl : BroadcastReceiver(),
    NetworkReceiver {
    override fun onReceive(context: Context, intent: Intent) {
        val manager = context
            .getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val wifi = manager.getNetworkInfo(TYPE_WIFI)?.isConnected ?: false

        val mobile = manager.getNetworkInfo(TYPE_MOBILE)?.isConnected ?: false

        (wifi || mobile).let { isOnline ->
            if (isOnline) onConnectionOnline() else onConnectionOffline()
        }
    }

    override fun onConnectionOnline() {

    }

    override fun onConnectionOffline() {

    }

    override fun get(): BroadcastReceiver = this
}
