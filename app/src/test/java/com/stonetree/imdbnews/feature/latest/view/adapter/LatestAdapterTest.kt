package com.stonetree.imdbnews.feature.latest.view.adapter

import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class LatestAdapterTest {

    private lateinit var adapter: LatestAdapter

    @Before
    fun setup() {
        adapter = LatestAdapter()
    }

    @Test
    fun test_adapter_shouldReturnDefaultValues() {
        assertEquals(adapter.itemCount, 0)
    }
}
