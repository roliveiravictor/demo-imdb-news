package com.stonetree.shuttergallery.core.viewholder

import androidx.databinding.DataBindingUtil
import androidx.test.rule.ActivityTestRule
import com.stonetree.shuttergallery.R
import com.stonetree.shuttergallery.core.constants.Constants.IMAGE_URL
import com.stonetree.shuttergallery.databinding.ViewShutterBinding
import com.stonetree.shuttergallery.feature.shutter.view.ShutterView
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class BaseViewHolderTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(ShutterView::class.java)

    @Test
    fun test_baseViewHolderOnBind_shouldReturnSameUrl() {
        rule.runOnUiThread {
            val bind: ViewShutterBinding = DataBindingUtil
                .setContentView(
                    rule.activity,
                    R.layout.view_shutter
                )

            class ViewHolder : BaseViewHolder<String>(bind) {

                override fun onBind(data: String) {
                    assertEquals(IMAGE_URL, data)
                }
            }

            ViewHolder().onBind(IMAGE_URL)
        }
    }
}