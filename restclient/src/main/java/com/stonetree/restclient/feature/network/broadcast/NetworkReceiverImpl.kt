package com.stonetree.restclient.feature.network.broadcast

import android.net.ConnectivityManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.widget.Toast
import android.widget.Toast.*


class NetworkChangeReceiverImpl : BroadcastReceiver(), NetworkReceiver {

    override fun onReceive(context: Context, intent: Intent) {
        val manager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        val mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        (wifi!!.isConnected || mobile!!.isConnected).let { isOnline ->
            if (!isOnline) {
                makeText(
                    context,
                    "No network available.",
                    LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onConnectivityChange(context: Context) {

    }

    override fun get(): BroadcastReceiver = this
}
