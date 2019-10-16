package com.stonetree.imdbnews.feature.latest.view.adapter

import androidx.databinding.DataBindingUtil.setContentView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.restclient.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.databinding.ListItemLatestBinding
import com.stonetree.imdbnews.feature.latest.model.Movie
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LatestViewHolderTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

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
    fun test_viewHolderWithNullId_shouldReturnUrlWithoutListener() {
        val movie = Movie()
        movie.poster = BASE_URL

        vh.onBind(movie)
        assertEquals(BASE_URL, bind.url)
        assertNull(bind.listener)
    }

    @Test
    fun test_viewHolderWithId_shouldReturnUrlWithListener() {
        val movie = Movie()
        movie.poster = BASE_URL
        movie.id = 1L

        vh.onBind(movie)
        assertEquals(BASE_URL, bind.url)
        assertNotNull(bind.listener)
    }
}