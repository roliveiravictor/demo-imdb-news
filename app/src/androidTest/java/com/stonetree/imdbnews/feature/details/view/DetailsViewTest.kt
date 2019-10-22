package com.stonetree.imdbnews.feature.details.view

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.stonetree.restclient.feature.idling.RestClientIdling
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.core.constants.Constants.APP_TITLE
import com.stonetree.imdbnews.core.constants.Constants.MOVIE_ID_KEY
import com.stonetree.imdbnews.core.constants.Constants.MOVIE_ID_VALUE
import com.stonetree.imdbnews.core.constants.Constants.PACKAGE
import com.stonetree.imdbnews.core.extensions.execute
import com.stonetree.imdbnews.core.extensions.launchFragmentScenario
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    private var fragment: DetailsView? = null

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(RestClientIdling.getResource())
        jumpToDetailsViewFragment()
    }

    private fun jumpToDetailsViewFragment() {
        val bundle = Bundle()
        bundle.putLong(MOVIE_ID_KEY, MOVIE_ID_VALUE)

        rule.activity
            .findNavController(R.id.imdb_nav_fragment)
            .launchFragmentScenario(bundle, DetailsView())
            .execute { fragment ->
                this@DetailsViewTest.fragment = fragment
            }
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(RestClientIdling.getResource())
    }

    @Test
    fun test_lazyVm_shouldReturnNotNull() {
        assertNotNull(fragment?.vm)
    }

    @Test
    fun test_bindObservers_shouldReturnNothingNull() {
        fragment?.vm?.apply {
            assertTrue(details.hasObservers())
            assertTrue(network.hasObservers())
        }
    }

    @Test
    fun test_useAppContext_shouldReturnPackageName() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(PACKAGE, context.packageName)
    }

    @Test
    fun test_loadingVisibility_shouldReturnGone() {
        onView(withId(R.id.core_loading_view))
            .check(matches(withEffectiveVisibility(GONE)))
    }

    @Test
    fun test_detailsVisibility_shouldReturnVisible() {
        onView(withId(R.id.root_details_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_screenRotation_shouldKeepTitle() {
        rule.activity.requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        assertEquals(rule.activity.title, APP_TITLE)
    }

    @Test
    fun test_detailsText_shouldReturnFilled() {
        onView(withId(R.id.title))
                .check(matches(not(withText(""))))

        onView(withId(R.id.description))
                    .check(matches(not(withText(""))))
    }
}