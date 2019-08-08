package com.stonetree.shuttergallery.feature.shutter.view

import android.content.pm.ActivityInfo
import android.widget.GridLayout.VERTICAL
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.stonetree.corerepository.feature.idling.CoreRepositoryIdling
import kotlinx.android.synthetic.main.view_shutter.*
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import com.stonetree.shuttergallery.R
import com.stonetree.shuttergallery.core.constants.Constants.APP_TITLE
import com.stonetree.shuttergallery.core.constants.Constants.IMAGE_URL
import com.stonetree.shuttergallery.core.constants.Constants.PACKAGE
import org.hamcrest.CoreMatchers.`is`

@RunWith(AndroidJUnit4::class)
class ShutterViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(ShutterView::class.java)

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
        assertNotNull(rule.activity.shutters.adapter)
    }

    @Test
    fun test_bindObservers_shouldReturnNothingNull() {
        assertTrue(rule.activity.vm.shutters.hasObservers())
        assertTrue(rule.activity.vm.network.hasObservers())
    }

    @Test
    fun test_loadingVisibility_shouldReturnVisible() {
        onView(withId(R.id.loading))
            .check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun test_shuttersVisibility_shouldReturnVisible() {
        onView(withId(R.id.shutters))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun test_screenRotation_shouldKeepTitle() {
        rule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        assertEquals(rule.activity.title, APP_TITLE)
    }

    @Test
    fun test_shuttersCache_shouldNotBeEmpty() {
        onView(withId(R.id.shutters))
            .perform(swipeUp())

        assertNotEquals(rule.activity.shutters.adapter?.itemCount, 0)
    }

    @Test(expected = PerformException::class)
    fun test_loadingClick_shouldThrowException() {
        onView(withId(R.id.loading))
            .perform(click())
    }

    @Test
    fun test_shuttersClick_shouldDoNothing() {
        onView(withId(R.id.shutters))
            .perform(click())
    }

    @Test
    fun test_shuttersInitialLoading_shouldReturnTagZero() {
        onView(
            allOf(
            withId(R.id.image),
            withTagValue(`is`(IMAGE_URL)))
        ).check(matches(isDisplayed()))
    }

    @Test
    fun test_recyclerView_shouldReturnDefaultValues() {
        rule.activity.shutters.apply {
            assertTrue(layoutManager is GridLayoutManager)
            val grid = (layoutManager as GridLayoutManager)
            grid.apply {
                assertTrue(spanCount == 2)
                assertTrue(orientation == VERTICAL)
            }
        }
    }
}