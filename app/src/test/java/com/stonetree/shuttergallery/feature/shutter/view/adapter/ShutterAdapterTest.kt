package com.stonetree.shuttergallery.feature.shutter.view.adapter

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class ShutterAdapterTest {

    private lateinit var adapter: ShutterAdapter

    @Before
    fun setup() {
        adapter = ShutterAdapter()
    }

    @Test
    fun test_adapter_shouldReturnDefaultValues() {
        assertEquals(adapter.itemCount, 0)
    }
}
