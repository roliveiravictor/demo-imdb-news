package com.stonetree.restclient.core.extensions

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.stonetree.restclient.core.constants.RestclientConstants.BASE_URL
import com.stonetree.restclient.core.constants.RestclientConstants.TEST_PROPS
import com.stonetree.restclient.feature.idling.RestClientIdling.getResource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import okhttp3.Request
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class RestclientExtensionsTest {

    private val context = getInstrumentation().targetContext

    private class CallTest : Call<String> {
        override fun enqueue(callback: Callback<String>) {
            // Do Nothing
        }

        override fun isExecuted(): Boolean {
            TODO("not implemented")
        }

        override fun clone(): Call<String> {
            TODO("not implemented")
        }

        override fun isCanceled(): Boolean {
            TODO("not implemented")
        }

        override fun cancel() {
            TODO("not implemented")
        }

        override fun execute(): Response<String> {
            TODO("not implemented")
        }

        override fun request(): Request {
            TODO("not implemented")
        }
    }

    @Test
    fun test_enqueue_shouldIncrementIdling() {
        CallTest().enqueue(MutableLiveData()) {
            // Do Nothing
        }
        assertFalse(getResource().isIdleNow)
    }

    @Test
    fun test_read_shouldReturnProperty() {
        assertEquals(
            "mUrl",
            TEST_PROPS.read(context, BASE_URL)
        )
    }
}