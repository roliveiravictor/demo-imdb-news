package com.stonetree.imdbnews.feature.latest.res.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.restclient.core.extensions.enqueue
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class LatestRepositoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val client = RestClientImpl()

    @Before
    fun setup() {
        client.start(context)
    }

    /**
     *  https://stackoverflow.com/questions/33120493/espresso-idling-resource-doesnt-work
     *
     *  Weird behavior from IdlingResources. Basically just works when some onView functionality is triggered.
     *
     *  "Espresso provides a sophisticated set of synchronization capabilities.
     *   This characteristic of the framework, however, applies only to operations
     *   that post messages on the MessageQueue, such as a subclass of View that's
     *   drawing its contents on the screen."
     *
     *   https://developer.android.com/training/testing/espresso/idling-resource
     *
     *   (One) Recommendation for this scenario would be use of CountDownLatch
     */
    @Test
    fun test_getRequest_shouldReturnNotDefaultValues() {
        val countdown = CountDownLatch(1)
        val request: Call<LatestModel> = client.generate(LatestApi::class).get(1, client.key())
        request.enqueue {
            onResponse = { response ->
                assertGetRequests(response)
                countdown.countDown()
            }

            onFailure = { error ->
                assertNotEquals("", error?.message)
                countdown.countDown()
            }
        }
        countdown.await()
    }

    private fun assertGetRequests(response: Response<LatestModel>) {
        if (response.isSuccessful) {
            response.body()?.apply {
                assertNotEquals(-1L, page)
                assertNotEquals(-1, totalPages)
                assertNotNull(results)

                results?.forEach { movie ->
                    assertNotEquals(-1, movie.id)
                    assertNotEquals("", movie.poster)
                }
            }
        } else {
            assertNotEquals(200, response.code())
        }
    }
}