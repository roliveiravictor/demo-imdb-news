package com.stonetree.imdbnews.feature.latest.model

import junit.framework.TestCase.assertNull
import org.junit.Test

class MovieTest {

    @Test
    fun test_latestModel_shouldReturnDefaultValues() {
        Movie().apply {
            assertNull(id)
            assertNull(poster)
        }
    }
}