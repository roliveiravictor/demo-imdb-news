package com.stonetree.imdbnews.feature.details.res.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.corerepository.core.extensions.enqueue
import com.stonetree.corerepository.feature.repository.CoreRepository
import com.stonetree.imdbnews.core.constants.Constants.MOVIE_ID
import com.stonetree.imdbnews.feature.details.model.DetailsModel
import junit.framework.TestCase.*
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Response
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class DetailsRepositoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val core = CoreRepository.start(context)

    private val repository = DetailsRepository.getInstance()

    @Test
    fun test_api_shouldReturnObject() {
        assertNotNull(repository.api)
    }

    @Test
    fun test_getRequest_shouldReturnNotDefaultValues() {
        val countdown = CountDownLatch(1)
        val request: Call<DetailsModel> = repository.api.get(MOVIE_ID)
        request.enqueue {
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
        if(response.isSuccessful) {
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