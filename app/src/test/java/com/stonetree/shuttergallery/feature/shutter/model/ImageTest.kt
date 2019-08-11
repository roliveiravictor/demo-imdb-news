package com.stonetree.shuttergallery.feature.shutter.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ImageTest {

    @Test
    fun test_image_shouldReturnDefaultValues() {
        val assets = Assets()
        val image = Image(-1, -1f, assets)
        assertEquals(-1, image.id)
        assertEquals(-1f, image.aspect)
        assertEquals(assets, image.assets)
    }
}
