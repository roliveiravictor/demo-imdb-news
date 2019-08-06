package com.stonetree.shuttergallery.feature.shutter.model

import junit.framework.TestCase.assertNotNull
import org.junit.Test

class AssetsTest {

    @Test
    fun test_assets_shouldReturnThumb() {
        val assets = Assets()
        assets.thumb = Thumb()
        assertNotNull(assets.thumb)
    }
}