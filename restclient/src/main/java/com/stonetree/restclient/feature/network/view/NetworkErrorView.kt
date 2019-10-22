package com.stonetree.restclient.feature.network.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stonetree.restclient.R
import com.stonetree.restclient.feature.network.broadcast.NetworkReceiver
import org.koin.android.ext.android.inject

class NetworkErrorView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_network_error)
    }

    override fun onBackPressed() {

    }
}