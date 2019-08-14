package com.stonetree.imdbnews.feature.latest.model

import junit.framework.TestCase.*
import org.junit.Test

class LatestModelTest {

    @Test
    fun test_latestModel_shouldReturnDefaultValues() {
        val model = LatestModel()
        assertNull(model.page)
        assertNull(model.totalPages)
        assertNull(model.results)
    }

    @Test
    fun test_latestModel_shouldReturnData() {
        val model = LatestModel()
        model.results = arrayListOf()
        assertNotNull(model.results)
    }
}
