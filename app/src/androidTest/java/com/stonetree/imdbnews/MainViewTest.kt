package com.stonetree.imdbnews

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase.assertNotNull
import kotlinx.android.synthetic.main.view_main.*
import org.junit.Rule
import org.junit.Test

class MainViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    @Test
    fun test_findNavFragment_shouldReturnNotNull() {
        assertNotNull(rule.activity.imdb_nav_fragment)
    }

    @Test
    fun test_navigationToLatestView_shouldNavigateToLatestFragment() {
        rule.activity.apply {
            runOnUiThread {
                findNavController(R.id.imdb_nav_fragment).navigate(R.id.latest_view)
            }
        }
        onView(withId(R.id.latest)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navigationToDetailsView_shouldNavigateToDetailsFragment() {
        rule.activity.apply {
            runOnUiThread {
                findNavController(R.id.imdb_nav_fragment).navigate(R.id.details_view)
            }
        }
        onView(withId(R.id.poster)).check(matches(isDisplayed()))
    }

}