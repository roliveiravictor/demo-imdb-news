package com.stonetree.corerepository.feature.callback

import com.stonetree.corerepository.feature.idling.CoreRepositoryIdling
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import retrofit2.Call
import retrofit2.Response

class CoreRepositoryCallbackTest {

    private lateinit var callback : CoreRepositoryCallback<String>

    @Before
    fun setup() {
        callback = CoreRepositoryCallback()
    }

    @Test
    fun test_callback_shouldReturnDefaultValues(){
        callback.apply {
            assertNull(onResponse)
            assertNull(onFailure)
        }
    }

    @Test
    fun test_onResponse_shouldDecrementIdling(){
        CoreRepositoryIdling.getResource().increment()

        val call = mock(Call::class.java) as Call<String>
        val response = mock(Response::class.java) as Response<String>

        callback.onResponse(call, response)

        assertTrue(CoreRepositoryIdling.getResource().isIdleNow)
    }

    @Test
    fun test_onFailure_shouldDecrementIdling(){
        CoreRepositoryIdling.getResource().increment()

        val call = mock(Call::class.java) as Call<String>
        val throwable = mock(Throwable::class.java)

        callback.onFailure(call, throwable)

        assertTrue(CoreRepositoryIdling.getResource().isIdleNow)
    }
}
