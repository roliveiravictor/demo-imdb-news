package com.stonetree.imdbnews

import android.os.Bundle
import androidx.navigation.findNavController
import com.stonetree.view.feature.CoreActivity

class MainView : CoreActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_main)
        findNavController(R.id.imdb_nav_fragment)
    }
}
