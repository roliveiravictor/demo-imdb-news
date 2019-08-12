package com.stonetree.imdbnews.feature.latest.model

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Test

class LatestModelTest {

    @Test
    fun test_latestModel_shouldReturnDefaultValues() {
        val model = LatestModel()
        assertEquals(-1L, model.page)
        assertEquals(-1, model.perPage)
        assertEquals(-1L, model.totalCount)
        assertEquals("", model.searchId)
    }

    @Test
    fun test_latestModel_shouldReturnData() {
        val model = LatestModel()
        model.data = arrayListOf()
        assertNotNull(model.data)
    }
}
