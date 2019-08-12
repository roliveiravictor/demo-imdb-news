package com.stonetree.imdbnews.feature.latest.view.adapter

import com.stonetree.imdbnews.feature.latest.model.Movie
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class LatestDiffCallbackTest {

    private lateinit var callback: LatestDiffCallback

    @Before
    fun setup() {
        callback = LatestDiffCallback()
    }

    @Test
    fun test_sameContents_shouldReturnSameUrl() {
        assertTrue(callback.areContentsTheSame(createMovie(), createMovie()))
    }

    @Test
    fun test_sameItems_shouldReturnSameObject() {
        val movie = createMovie()
        assertTrue(callback.areItemsTheSame(movie, movie))
    }

    private fun createMovie() : Movie {
        return Movie()
    }
}
