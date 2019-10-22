package com.stonetree.imdbnews

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.navigation.findNavController
import com.stonetree.imdbnews.core.activity.DummyActivity
import com.stonetree.restclient.feature.network.broadcast.NetworkReceiver
import com.stonetree.restclient.feature.repository.RestClient
import com.stonetree.view.feature.core.CoreActivity
import org.koin.android.ext.android.inject

class MainActivity : DummyActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_main)
        findNavController(R.id.imdb_nav_fragment)
    }
}
