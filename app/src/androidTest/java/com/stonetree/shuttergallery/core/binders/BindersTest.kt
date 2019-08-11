package com.stonetree.shuttergallery.core.binders

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.shuttergallery.core.constants.Constants.IMAGE_URL
import com.stonetree.shuttergallery.feature.shutter.view.ShutterView
import junit.framework.TestCase.assertEquals
import kotlinx.android.synthetic.main.view_shutter.loading
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BindersTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(ShutterView::class.java)

    @Test
    fun test_bindIsGone_shouldReturnVisible() {
        rule.activity.apply {
            runOnUiThread {
                bindIsGone(this.loading, NetworkState.LOADING)
                assertEquals(this.loading.visibility, View.VISIBLE)
            }
        }
    }

    @Test
    fun test_bindIsGone_shouldReturnGone() {
        rule.activity.apply {
            runOnUiThread {
                bindIsGone(this.loading, NetworkState.LOADED)
                assertEquals(this.loading.visibility, View.GONE)
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
                bindLoadImage(view, IMAGE_URL)
                assertEquals(view.tag, IMAGE_URL)
            }
    }
}