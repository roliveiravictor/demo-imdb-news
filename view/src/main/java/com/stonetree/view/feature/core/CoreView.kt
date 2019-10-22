package com.stonetree.view.feature.core

import android.content.BroadcastReceiver

interface CoreView {

    fun onRegisterReceiver(receiver: BroadcastReceiver)

    fun onUnregisterReceiver(receiver: BroadcastReceiver)

}