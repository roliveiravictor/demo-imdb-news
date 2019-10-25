package com.stonetree.imdbnews.core.activity

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.core.constants.Actions.NAVIGATOR
import com.stonetree.restclient.core.constants.RestclientConstants.ACTIONS.NETWORK_ERROR
import com.stonetree.restclient.feature.network.NetworkReceiver
import com.stonetree.view.feature.activity.CoreActivity
import org.koin.android.ext.android.inject

open class MainActivity : CoreActivity() {

    private val receiver: NetworkReceiver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiver.registerOfflineIntent(
            NETWORK_ERROR,
            getString(R.string.offline_message)
        )
        receiver.registerOnlineIntent(NAVIGATOR)
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            receiver.get(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver.get())
    }
}