package com.stonetree.imdbnews.core.binders

import android.view.View
import android.widget.ImageView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.core.constants.Constants.IMAGE_BASE_URL
import com.stonetree.imdbnews.core.constants.Constants.IMAGE_PATH
import junit.framework.TestCase.assertEquals
import kotlinx.android.synthetic.main.view_latest.latest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BindersTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    @Test
    fun test_bindIsIdleLoading_shouldReturnGone() {
        rule.activity.apply {
            runOnUiThread {
                bindIsIdle(latest, NetworkState.LOADING)
                assertEquals(latest.visibility, View.GONE)
            }
        }
    }

    @Test
    fun test_bindIsIdleError_shouldReturnVisible() {
        rule.activity.apply {
            runOnUiThread {
                bindIsIdle(latest, NetworkState.error(""))
                assertEquals(latest.visibility, View.VISIBLE)
            }
        }
    }

    @Test
    fun test_bindIsIdleLoaded_shouldReturnVisible() {
        rule.activity.apply {
            runOnUiThread {
                bindIsIdle(latest, NetworkState.LOADED)
                assertEquals(latest.visibility, View.VISIBLE)
            }
        }
    }

    @Test
    fun test_bindLoadImage_shouldReturnUrlAsTag() {
        rule.activity.apply {
            runOnUiThread {
                ImageView(this).let { view ->
                    bindLoadImage(view, IMAGE_PATH)
                    assertEquals(view.tag, IMAGE_BASE_URL + IMAGE_PATH)
                }
            }
        }
    }
}