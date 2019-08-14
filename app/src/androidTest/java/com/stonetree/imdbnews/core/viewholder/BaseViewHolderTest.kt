package com.stonetree.imdbnews.core.viewholder

import androidx.databinding.DataBindingUtil
import androidx.test.rule.ActivityTestRule
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.core.constants.Constants.IMAGE_PATH
import com.stonetree.imdbnews.databinding.ViewLatestBinding
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class BaseViewHolderTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    @Test
    fun test_baseViewHolderOnBind_shouldReturnSameUrl() {
        rule.runOnUiThread {
            val bind: ViewLatestBinding = DataBindingUtil
                .setContentView(
                    rule.activity,
                    R.layout.view_latest
                )

            class ViewHolder : BaseViewHolder<String>(bind) {

                override fun onBind(data: String) {
                    assertEquals(IMAGE_PATH, data)
                }
            }

            ViewHolder().onBind(IMAGE_PATH)
        }
    }
}