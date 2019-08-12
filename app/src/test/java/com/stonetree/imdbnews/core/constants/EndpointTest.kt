package com.stonetree.imdbnews.core.constants

import com.stonetree.imdbnews.core.constants.Endpoint.LATEST_PATH
import junit.framework.TestCase.assertEquals
import org.junit.Test

class EndpointTest {

    @Test
    fun test_imagesPath_shouldReturnSame() {
        assertEquals( "images/search",LATEST_PATH)
    }
}
