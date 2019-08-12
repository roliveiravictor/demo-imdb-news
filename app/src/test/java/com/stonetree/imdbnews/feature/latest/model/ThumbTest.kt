package com.stonetree.imdbnews.feature.latest.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ThumbTest {

    @Test
    fun test_thumb_shouldReturnDefaultValues() {
        val thumb = Thumb()
        assertEquals(-1, thumb.height)
        assertEquals(-1, thumb.width)
        assertEquals("", thumb.url)
    }
}
