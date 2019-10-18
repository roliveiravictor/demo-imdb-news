package com.stonetree.imdbnews.feature.latest.view

import android.content.pm.ActivityInfo
import android.widget.GridLayout.VERTICAL
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.PerformException
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.stonetree.restclient.feature.idling.RestClientIdling
import kotlinx.android.synthetic.main.view_latest.latest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNotEquals
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE
import androidx.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.core.constants.Constants.APP_TITLE
import com.stonetree.imdbnews.core.constants.Constants.IMAGE_BASE_URL
import com.stonetree.imdbnews.core.constants.Constants.IMAGE_PATH
import com.stonetree.imdbnews.core.constants.Constants.PACKAGE
import com.stonetree.imdbnews.core.extensions.launchFragmentScenario
import com.stonetree.restclient.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.imdbnews.core.extensions.execute
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.`is`
import org.junit.*

@RunWith(AndroidJUnit4::class)
class LatestViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    private var fragment: LatestView? = null

    @Before
    fun setup() {
        Intents.init()
        IdlingRegistry.getInstance().register(RestClientIdling.getResource())
        jumpToLatestViewFragment()
    }

    private fun jumpToLatestViewFragment() {
        rule.activity
            .findNavController(R.id.imdb_nav_fragment)
            .launchFragmentScenario(null, LatestView())
            .execute { fragment ->
                this@LatestViewTest.fragment = fragment
            }
    }

    @After
    fun clean() {
        IdlingRegistry.getInstance().unregister(RestClientIdling.getResource())
        Intents.release()
    }

    @Test
    fun test_useAppContext_shouldReturnPackageName() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(PACKAGE, context.packageName)
    }

    @Test
    fun test_bindXml_shouldReturnNothingNull() {
        assertNotNull(rule.activity.latest.adapter)
    }

    @Test
    fun test_loadingVisibility_shouldReturnVisible() {
        onView(withId(R.id.loading))
            .check(matches(withEffectiveVisibility(GONE)))
    }

    @Test
    fun test_latestVisibility_shouldReturnVisible() {
        onView(withId(R.id.latest))
            .check(matches(withEffectiveVisibility(VISIBLE)))
    }

    @Test
    fun test_screenRotation_shouldKeepTitle() {
        rule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        assertEquals(rule.activity.title, APP_TITLE)
    }

    @Test
    fun test_latestSwipe_shouldNotBeEmpty() {
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
    @Ignore("Implement mock server")
    fun test_latestInitialLoading_shouldReturnTagZero() {
        onView(
            allOf(
                withId(R.id.poster),
                withTagValue(`is`(IMAGE_BASE_URL + IMAGE_PATH))
            )
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