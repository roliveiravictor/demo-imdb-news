package com.stonetree.imdbnews.feature.latest.view.adapter

import androidx.databinding.DataBindingUtil.setContentView
import androidx.test.rule.ActivityTestRule
import com.stonetree.corerepository.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.databinding.ListItemLatestBinding
import com.stonetree.imdbnews.feature.latest.view.LatestView
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LatestViewHolderTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(LatestView::class.java)

    private lateinit var bind: ListItemLatestBinding
    private lateinit var vh: LatestViewHolder

    @Before
    fun setup() {
        rule.runOnUiThread {
            bind = setContentView(rule.activity, R.layout.list_item_latest)
            vh = LatestViewHolder(bind)
        }
    }

    @Test
    fun test_vh_shouldReturnDefaultValues() {
        vh.onBind(BASE_URL)
        assertEquals(BASE_URL, bind.url)
    }
}