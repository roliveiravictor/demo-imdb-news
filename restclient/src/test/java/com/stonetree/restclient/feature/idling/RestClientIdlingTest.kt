package com.stonetree.restclient.feature.idling

import com.stonetree.restclient.feature.idling.RestClientIdling.getResource
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test

class RestClientIdlingTest {

    @Before
    fun setup() {
        getResource().clear()
    }

    @Test
    fun test_increment_shouldReturnIdle() {
        getResource().increment()
        assertFalse(getResource().isIdleNow)
    }

    @Test
    fun test_decrement_shouldReturnNotIdle() {
        getResource().increment()
        getResource().decrement()
        assertTrue(getResource().isIdleNow)
    }

    @Test
    fun test_name_shouldReturnGlobal() {
        assertEquals("GLOBAL", getResource().name)
    }

    @Test(expected = IllegalArgumentException::class)
    fun test_decrementWithoutIncrement_shouldReturnThrowException() {
        getResource().decrement()
    }
}
