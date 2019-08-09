package com.stonetree.shuttergallery.feature.shutter.view.adapter

import androidx.databinding.DataBindingUtil.setContentView
import androidx.test.rule.ActivityTestRule
import com.stonetree.corerepository.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.shuttergallery.R
import com.stonetree.shuttergallery.databinding.ListItemShutterBinding
import com.stonetree.shuttergallery.feature.shutter.view.ShutterView
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShutterViewHolderTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(ShutterView::class.java)

    private lateinit var bind: ListItemShutterBinding
    private lateinit var vh: ShutterViewHolder

    @Before
    fun setup() {
        rule.runOnUiThread {
            bind = setContentView(rule.activity, R.layout.list_item_shutter)
            vh = ShutterViewHolder(bind)
        }
    }

    @Test
    fun test_vh_shouldReturnDefaultValues() {
        vh.onBind(BASE_URL)
        assertEquals(BASE_URL, bind.url)
    }
}