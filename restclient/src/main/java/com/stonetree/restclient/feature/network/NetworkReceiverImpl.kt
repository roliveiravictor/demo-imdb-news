package com.stonetree.restclient.feature.network

import android.app.Activity
import android.net.ConnectivityManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.*
import android.content.Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.net.ConnectivityManager.*
import com.stonetree.restclient.feature.view.NetworkErrorActivity

class NetworkChangeReceiverImpl : BroadcastReceiver(), NetworkReceiver {

    private val onlineIntent = Intent().apply {
        this.flags = FLAG_ACTIVITY_BROUGHT_TO_FRONT
        this.flags = FLAG_ACTIVITY_CLEAR_TOP
    }

    private val offlineIntent = Intent().apply {
        this.flags = FLAG_ACTIVITY_BROUGHT_TO_FRONT
        this.flags = FLAG_ACTIVITY_CLEAR_TOP
    }

    override fun onReceive(context: Context, intent: Intent) {
        val manager = context
            .getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        val wifi = manager.getNetworkInfo(TYPE_WIFI)?.isConnected ?: false

        val mobile = manager.getNetworkInfo(TYPE_MOBILE)?.isConnected ?: false

        (wifi || mobile).let { isOnline ->
            if (isOnline)
                onConnectionOnline(context)
            else
                onConnectionOffline(context)
        }
    }

    override fun get(): BroadcastReceiver = this

    override fun registerOfflineIntent(action: String, message: String) {
        offlineIntent.apply {
            this.action = action
            putExtra(offlineMessageKey(), message)
        }
    }

    override fun registerOnlineIntent(action: String) {
        onlineIntent.apply {
            this.action = action
        }
    }

    override fun offlineMessageKey(): String = "network_offline_error_message"

    private fun onConnectionOnline(context: Context) {
        (context as? Activity)?.let { activity ->
            when (activity) {
                is NetworkErrorActivity -> launchOnlineIntent(activity, context)
            }
        }
    }

    private fun onConnectionOffline(context: Context) {
        context.startActivity(offlineIntent)
    }

    private fun launchOnlineIntent(
        activity: Activity,
        context: Context
    ) {
        offlineIntent.action?.apply {
            if (this == activity.intent.action) {
                context.startActivity(onlineIntent)
            }
        }
    }
}
