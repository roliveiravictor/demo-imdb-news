package com.stonetree.shuttergallery.feature.shutter.model

import junit.framework.TestCase.*
import org.junit.Test

class ShutterModelTest {

    @Test
    fun test_shutterModel_shouldReturnDefaultValues() {
        val model = ShutterModel()
        assertEquals(-1L, model.page)
        assertEquals(-1, model.perPage)
        assertEquals(-1L, model.totalCount)
        assertEquals("", model.searchId)
    }

    @Test
    fun test_shutterModel_shouldReturnData() {
        val model = ShutterModel()
        model.data = arrayListOf()
        assertNotNull(model.data)
    }
}