package com.stonetree.imdbnews.core.binders

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.core.constants.Constants.IMAGE_BASE_URL
import com.stonetree.imdbnews.core.constants.Constants.IMAGE_PATH
import junit.framework.TestCase.assertEquals
import kotlinx.android.synthetic.main.view_latest.loading
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BindersTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    @Test
    fun test_bindIsGone_shouldReturnVisible() {
        rule.activity.apply {
            runOnUiThread {
                bindIsLoading(this.loading, NetworkState.LOADING)
                assertEquals(this.loading.visibility, View.VISIBLE)
            }
        }
    }

    @Test
    fun test_bindIsGoneLoaded_shouldReturnGone() {
        rule.activity.apply {
            runOnUiThread {
                bindIsLoading(this.loading, NetworkState.LOADED)
                assertEquals(this.loading.visibility, View.GONE)
            }
        }
    }

    @Test
    fun test_bindIsGoneError_shouldReturnGone() {
        rule.activity.apply {
            runOnUiThread {
                bindIsLoading(this.loading, NetworkState.error("mError"))
                assertEquals(this.loading.visibility, View.GONE)
            }
        }
    }

    @Test
    fun test_bindIsIdleLoading_shouldReturnGone() {
        rule.activity.apply {
            runOnUiThread {
                bindIsIdle(this.loading, NetworkState.LOADING)
                assertEquals(this.loading.visibility, View.GONE)
            }
        }
    }

    @Test
    fun test_bindIsIdleError_shouldReturnVisible() {
        rule.activity.apply {
            runOnUiThread {
                bindIsIdle(this.loading, NetworkState.error(""))
                assertEquals(this.loading.visibility, View.VISIBLE)
            }
        }
    }

    @Test
    fun test_bindIsIdleLoaded_shouldReturnVisible() {
        rule.activity.apply {
            runOnUiThread {
                bindIsIdle(this.loading, NetworkState.LOADED)
                assertEquals(this.loading.visibility, View.VISIBLE)
            }
        }
    }

    @Test
    fun test_bindLoadImage_shouldReturnUrlAsTag() {
        getInstrumentation()
            .targetContext
            .apply {
                Fresco.initialize(this)
                val view = SimpleDraweeView(this)
                bindLoadImage(view, IMAGE_PATH)
                assertEquals(view.tag, IMAGE_BASE_URL + IMAGE_PATH)
            }
    }
}