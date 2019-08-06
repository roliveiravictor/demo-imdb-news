package com.stonetree.shuttergallery.constants

import junit.framework.TestCase.assertEquals
import org.junit.Test

class EndpointTest {

    @Test
    fun test_imagesPath_shouldReturnSame() {
        assertEquals(Endpoint.IMAGES_PATH, "images/search")
    }
}