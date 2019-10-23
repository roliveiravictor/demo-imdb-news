package com.stonetree.restclient.feature.view

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stonetree.restclient.R
import com.stonetree.restclient.feature.network.NetworkReceiver
import kotlinx.android.synthetic.main.view_error_network.*
import org.koin.android.ext.android.inject

class NetworkErrorActivity : AppCompatActivity() {

    private val receiver: NetworkReceiver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_error_network)
        bindLayout()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(
            receiver.get(),
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onBackPressed() {
        
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver.get())
    }

    private fun bindLayout() {
        message.text = intent.getStringExtra(receiver.offlineMessageKey())
    }
}
