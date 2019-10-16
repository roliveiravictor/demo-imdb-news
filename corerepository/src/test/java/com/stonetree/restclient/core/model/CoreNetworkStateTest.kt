package com.stonetree.restclient.core.model

import com.stonetree.restclient.core.model.Status.SUCCESS
import com.stonetree.restclient.core.model.Status.RUNNING
import com.stonetree.restclient.core.model.Status.FAILED
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class CoreNetworkStateTest {

    @Test
    fun test_loaded_ShouldReturnRunning() {
        val state = NetworkState.LOADED
        assertEquals(SUCCESS, state.status)
        assertNull(state.msg)
    }

    @Test
    fun test_loading_ShouldReturnRunning() {
        val state = NetworkState.LOADING
        assertEquals(RUNNING, state.status)
        assertNull(state.msg)
    }

    @Test
    fun test_failed_ShouldReturnRunning() {
        val state = NetworkState.error("mError")
        assertEquals(FAILED, state.status)
        assertEquals("mError", state.msg)
    }
}
