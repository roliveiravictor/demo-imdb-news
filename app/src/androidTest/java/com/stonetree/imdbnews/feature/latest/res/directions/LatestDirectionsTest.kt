package com.stonetree.imdbnews.feature.latest.res.directions

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.imdbnews.MainActivity
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.core.constants.Constants.MOVIE_ID_KEY
import com.stonetree.imdbnews.core.constants.Constants.MOVIE_ID_VALUE
import com.stonetree.imdbnews.core.extensions.execute
import com.stonetree.imdbnews.core.extensions.launchFragmentScenario
import com.stonetree.imdbnews.feature.details.view.DetailsView
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LatestDirectionsTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java)

    private var fragment: DetailsView? = null

    @Before
    fun setup() {
        jumpToDetailsViewFragment()
    }

    private fun jumpToDetailsViewFragment() {
        val bundle = Bundle()
        bundle.putLong(MOVIE_ID_KEY, MOVIE_ID_VALUE)

        rule.activity
            .findNavController(R.id.imdb_nav_fragment)
            .launchFragmentScenario(bundle, DetailsView())
            .execute { fragment ->
                this@LatestDirectionsTest.fragment = fragment
            }
    }

    @Test
    fun test_actionLatestToDetails_shouldReturnBundle() {
        fragment?.arguments?.apply {
            assertEquals(420818L, get(MOVIE_ID_KEY))
        }
    }
}