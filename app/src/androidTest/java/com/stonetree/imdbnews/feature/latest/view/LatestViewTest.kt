package com.stonetree.imdbnews.feature.latest.view

import android.content.pm.ActivityInfo
import android.widget.GridLayout.VERTICAL
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.stonetree.corerepository.feature.idling.CoreRepositoryIdling
import kotlinx.android.synthetic.main.view_latest.latest
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.core.constants.Constants.APP_TITLE
import com.stonetree.imdbnews.core.constants.Constants.IMAGE_URL
import com.stonetree.imdbnews.core.constants.Constants.PACKAGE
import org.hamcrest.CoreMatchers.`is`

@RunWith(AndroidJUnit4::class)
class LatestViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(LatestView::class.java)

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(CoreRepositoryIdling.getResource())
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(CoreRepositoryIdling.getResource())
    }

    @Test
    fun test_useAppContext_shouldReturnPackageName() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(PACKAGE, context.packageName)
    }

    @Test
    fun test_lazyVm_shouldReturnNotNull() {
        assertNotNull(rule.activity.vm)
    }

    @Test
    fun test_bindXml_shouldReturnNothingNull() {
        assertNotNull(rule.activity.latest.adapter)
    }

    @Test
    fun test_bindObservers_shouldReturnNothingNull() {
        assertTrue(rule.activity.vm.latest.hasObservers())
        assertTrue(rule.activity.vm.network.hasObservers())
    }

    @Test
    fun test_loadingVisibility_shouldReturnVisible() {
        onView(withId(R.id.loading))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun test_latestVisibility_shouldReturnVisible() {
        onView(withId(R.id.latest))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_screenRotation_shouldKeepTitle() {
        rule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        assertEquals(rule.activity.title, APP_TITLE)
    }

    @Test
    fun test_latestCache_shouldNotBeEmpty() {
        onView(withId(R.id.latest))
            .perform(swipeUp())

        assertNotEquals(rule.activity.latest.adapter?.itemCount, 0)
    }

    @Test(expected = PerformException::class)
    fun test_loadingClick_shouldThrowException() {
        onView(withId(R.id.loading))
            .perform(click())
    }

    @Test
    fun test_latestClick_shouldDoNothing() {
        onView(withId(R.id.latest))
            .perform(click())
    }

    @Test
    fun test_latestInitialLoading_shouldReturnTagZero() {
        onView(
            allOf(
            withId(R.id.image),
            withTagValue(`is`(IMAGE_URL)))
        ).check(matches(isDisplayed()))
    }

    @Test
    fun test_recyclerView_shouldReturnDefaultValues() {
        rule.activity.latest.apply {
            assertTrue(layoutManager is GridLayoutManager)
            val grid = (layoutManager as GridLayoutManager)
            grid.apply {
                assertTrue(spanCount == 2)
                assertTrue(orientation == VERTICAL)
            }
        }
    }
}