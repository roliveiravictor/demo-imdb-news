package com.stonetree.shuttergallery

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers.Visibility.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.stonetree.shuttergallery.feature.shutter.view.ShutterView
import kotlinx.android.synthetic.main.view_shutter.*
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShutterViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(ShutterView::class.java)

    @Test
    fun test_useAppContext_shouldReturnPackageName() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.stonetree.shuttergallery", appContext.packageName)
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
            .check(matches(withEffectiveVisibility(VISIBLE)))
    }

    @Test
    fun test_shuttersVisibility_shouldReturnVisible() {
        onView(withId(R.id.shutters))
            .check(matches(withEffectiveVisibility(VISIBLE)))
    }

    @Test
    fun test_screenRotation_shouldKeepTitle() {
        rule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        assertEquals(rule.activity.title, "Shutter Gallery")
    }

    @Test
    fun test_shuttersCache_shouldBeEmpty() {
        assertEquals(rule.activity.shutters.adapter?.itemCount, 0)
    }

    @Test(expected = PerformException::class)
    fun test_loadingClick_shouldThrowException() {
        onView(withId(R.id.loading))
            .perform(ViewActions.click())
    }

    @Test
    fun test_shuttersClick_shouldDoNothing() {
        onView(withId(R.id.shutters))
            .perform(ViewActions.click())
    }
}