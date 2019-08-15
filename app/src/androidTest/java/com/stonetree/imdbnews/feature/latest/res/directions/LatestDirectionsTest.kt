package com.stonetree.imdbnews.feature.latest.res.directions

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.core.constants.DirectionsBundleKey.MOVIE_ID
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
    val rule = ActivityTestRule(MainView::class.java)

    private var navigation: NavController? = null

    @Before
    fun setup() {
        navigation = rule.activity.findNavController(R.id.imdb_nav_fragment)
    }

    @Test
    fun test_actionLatestToDetails_shouldReturnBundle() {
        val direction = LatestDirections.actionLatestToDetails(-1)

        navigation?.apply {
            val fragment = launchFragmentScenario(direction.arguments, DetailsView(), this)
            fragment.onFragment { fragment ->
                assertEquals(-1L, fragment.arguments?.get(MOVIE_ID))
            }
        }
    }
}