package com.stonetree.corerepository.core.extensions

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.*
import com.stonetree.corerepository.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.corerepository.core.constants.RepositoryConstants.TEST_PROPS
import com.stonetree.corerepository.feature.idling.CoreRepositoryIdling
import com.stonetree.corerepository.feature.idling.CoreRepositoryIdling.getResource
import com.stonetree.corerepository.feature.repository.CoreRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import okhttp3.Request
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class RepositoryExtensionsTest {

    private val context = getInstrumentation().targetContext

    private class CallTest : Call<String> {
        override fun enqueue(callback: Callback<String>) {

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
    fun test_enqueue_shouldIncrementIdling(){
        CallTest().enqueue {

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