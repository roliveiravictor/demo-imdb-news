package com.stonetree.shuttergallery.core.constants

import com.stonetree.shuttergallery.core.constants.Endpoint.IMAGES_PATH
import junit.framework.TestCase.assertEquals
import org.junit.Test

class EndpointTest {

    @Test
    fun test_imagesPath_shouldReturnSame() {
        assertEquals( "images/search",IMAGES_PATH)
    }
}