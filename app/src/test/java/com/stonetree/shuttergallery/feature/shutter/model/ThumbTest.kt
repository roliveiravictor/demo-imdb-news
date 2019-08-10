package com.stonetree.shuttergallery.feature.shutter.model

import junit.framework.TestCase.*
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
