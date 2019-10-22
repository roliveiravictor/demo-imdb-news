package com.stonetree.view.feature.error

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stonetree.view.R
import com.stonetree.view.feature.core.CoreActivity

class NetworkErrorView : CoreActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_network_error)
    }

    override fun onBackPressed() {

    }
}