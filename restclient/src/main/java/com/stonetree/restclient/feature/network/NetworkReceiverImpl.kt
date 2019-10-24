package com.stonetree.restclient.feature.network

import android.annotation.SuppressLint
import android.app.Activity
import android.net.ConnectivityManager
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.*
import android.content.Intent.*
import android.net.ConnectivityManager.*
import com.stonetree.restclient.feature.view.NetworkErrorActivity

class NetworkChangeReceiverImpl : BroadcastReceiver(), NetworkReceiver {

    private val onlineIntent = Intent()

    private val offlineIntent = Intent()

    /** Protection Level = Normal **/
    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context, intent: Intent) {
        context.getSystemService(CONNECTIVITY_SERVICE)?.apply {
            (this as? ConnectivityManager)?.run {
                if (activeNetwork != null)
                    onConnectionOnline(context)
                else
                    onConnectionOffline(context)
            }
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
