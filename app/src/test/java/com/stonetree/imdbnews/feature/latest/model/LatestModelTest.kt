package com.stonetree.imdbnews.feature.latest.model

import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class LatestModelTest {

    @Test
    fun test_latestModel_shouldReturnDefaultValues() {
        LatestModel().apply {
            assertNull(page)
            assertNull(totalPages)
            assertNull(results)
        }
    }

    @Test
    fun test_latestModel_shouldReturnData() {
        LatestModel().apply {
            results = arrayListOf()
            assertNotNull(results)
        }
    }
}
