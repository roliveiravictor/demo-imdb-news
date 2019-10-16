package com.stonetree.restclient.feature.callback

import com.stonetree.restclient.feature.idling.RestClientIdling
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Call
import retrofit2.Response

class RestClientCallbackTest {

    private lateinit var callback: RestClientCallback<String>

    @Before
    fun setup() {
        callback = RestClientCallback()
    }

    @Test
    fun test_callback_shouldReturnDefaultValues() {
        callback.apply {
            assertNull(onResponse)
            assertNull(onFailure)
        }
    }

    @Test
    fun test_onResponse_shouldDecrementIdling() {
        RestClientIdling.getResource().increment()

        val call = mock(Call::class.java) as Call<String>
        val response = mock(Response::class.java) as Response<String>

        callback.onResponse(call, response)

        assertTrue(RestClientIdling.getResource().isIdleNow)
    }

    @Test
    fun test_onFailure_shouldDecrementIdling() {
        RestClientIdling.getResource().increment()

        val call = mock(Call::class.java) as Call<String>
        val throwable = mock(Throwable::class.java)

        callback.onFailure(call, throwable)

        assertTrue(RestClientIdling.getResource().isIdleNow)
    }
}
