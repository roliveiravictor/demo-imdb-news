package com.stonetree.imdbnews.core.constants

import com.stonetree.imdbnews.core.constants.DirectionsBundleKey.MOVIE_ID
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DirectionsBundleKeyTest {

    @Test
    fun test_directionsBundleKey_shouldReturnSame() {
        assertEquals("movie_id", MOVIE_ID)
    }
}