package com.stonetree.view.feature.core

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity

open class CoreActivity : AppCompatActivity(), CoreView {

    override fun onRegisterReceiver(receiver: BroadcastReceiver) {
        registerReceiver(
            receiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    override fun onUnregisterReceiver(receiver: BroadcastReceiver) {
        unregisterReceiver(receiver)
    }
}
