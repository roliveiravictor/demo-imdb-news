package com.stonetree.imdbnews.core.constants

import com.stonetree.imdbnews.core.constants.Endpoint.DETAILS_PATH
import com.stonetree.imdbnews.core.constants.Endpoint.LATEST_PATH
import junit.framework.TestCase.assertEquals
import org.junit.Test

class EndpointTest {

    @Test
    fun test_imagesPath_shouldReturnSame() {
        assertEquals( "movie/now_playing",LATEST_PATH)
        assertEquals( "movie/{movie_id}", DETAILS_PATH)
    }
}
