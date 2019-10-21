package com.stonetree.restclient.feature.network.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stonetree.restclient.R
import com.stonetree.restclient.feature.network.broadcast.NetworkReceiver
import org.koin.android.ext.android.inject

class NetworkErrorView : AppCompatActivity() {

    private val receiver: NetworkReceiver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_network_error)
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

    override fun onBackPressed() {

    }
}