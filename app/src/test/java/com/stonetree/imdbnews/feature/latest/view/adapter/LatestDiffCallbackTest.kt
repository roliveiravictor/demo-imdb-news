package com.stonetree.imdbnews.feature.latest.view.adapter

import com.stonetree.imdbnews.feature.latest.model.Assets
import com.stonetree.imdbnews.feature.latest.model.Image
import com.stonetree.imdbnews.feature.latest.model.Thumb
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
