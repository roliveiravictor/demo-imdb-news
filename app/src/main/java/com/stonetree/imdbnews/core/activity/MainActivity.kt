package com.stonetree.imdbnews.core.activity

import com.stonetree.restclient.feature.network.NetworkReceiver
import com.stonetree.view.feature.core.CoreActivity
import org.koin.android.ext.android.inject

open class MainActivity : CoreActivity() {

    private val receiver: NetworkReceiver by inject()

    override fun onResume() {
        super.onResume()
        onRegisterReceiver(receiver.get())
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver.get())
    }
}