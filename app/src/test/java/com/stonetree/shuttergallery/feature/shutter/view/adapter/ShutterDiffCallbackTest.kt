package com.stonetree.shuttergallery.feature.shutter.view.adapter

import com.stonetree.shuttergallery.feature.shutter.model.Assets
import com.stonetree.shuttergallery.feature.shutter.model.Image
import com.stonetree.shuttergallery.feature.shutter.model.Thumb
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class ShutterDiffCallbackTest {

    private lateinit var callback: ShutterDiffCallback

    @Before
    fun setup() {
        callback = ShutterDiffCallback()
    }

    @Test
    fun test_sameContents_shouldReturnSameUrl() {
        assertTrue(callback.areContentsTheSame(createImage(), createImage()))
    }

    @Test
    fun test_sameItems_shouldReturnSameObject() {
        val image = createImage()
        assertTrue(callback.areItemsTheSame(image, image))
    }

    private fun createImage() : Image {
        val thumb = Thumb()
        thumb.url = "mUrl"

        val asset = Assets()
        asset.thumb = thumb

        return Image(assets = asset)
    }
}