package com.stonetree.imdbnews.feature.details.res.repository

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.restclient.core.extensions.enqueue
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.imdbnews.core.constants.Constants.MOVIE_ID_VALUE
import com.stonetree.imdbnews.feature.details.model.DetailsModel
import com.stonetree.restclient.core.model.NetworkState
import junit.framework.TestCase.assertNotNull
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class DetailsRepositoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val client = RestClientImpl()

    private lateinit var repository: DetailsRepository

    @Before
    fun setup() {
        client.start(context)
        repository = DetailsRepository(client)
    }

    @Test
    fun test_api_shouldReturnObject() {
        assertNotNull(repository.api)
    }

    @Test
    fun test_getRequest_shouldReturnNotDefaultValues() {
        val countdown = CountDownLatch(1)
        val request: Call<DetailsModel> = repository.api.get(MOVIE_ID_VALUE, client.key())
        request.enqueue(MutableLiveData()) {
            onResponse = { response ->
                assertGetRequests(response)
                countdown.countDown()
            }

            onFailure = { error ->
                Assert.assertNotEquals("", error?.message)
                countdown.countDown()
            }
        }
        countdown.await()
    }

    private fun assertGetRequests(response: Response<DetailsModel>) {
        if (response.isSuccessful) {
            response.body()?.apply {
                assertNotNull(title)
                assertNotNull(description)
                assertNotNull(poster)
            }
        } else {
            Assert.assertNotEquals(200, response.code())
        }
    }
}