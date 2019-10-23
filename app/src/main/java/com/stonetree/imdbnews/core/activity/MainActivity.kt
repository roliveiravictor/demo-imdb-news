package com.stonetree.imdbnews.core.activity

import android.content.Intent.ACTION_MAIN
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import com.stonetree.restclient.feature.network.NetworkReceiver
import com.stonetree.view.feature.core.CoreActivity
import org.koin.android.ext.android.inject

open class MainActivity : CoreActivity() {

    private val receiver: NetworkReceiver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiver.registerOfflineIntent(
            "com.stonetree.restclient.feature.error.NetworkErrorActivity",
            "You're currently offline. Reconnecting..."
        )
        receiver.registerOnlineIntent("com.stonetree.imdbnews.NavigatorActivity")
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver.get(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver.get())
    }
}