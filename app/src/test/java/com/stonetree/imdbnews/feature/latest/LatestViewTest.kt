package com.stonetree.imdbnews.feature.latest

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.FragmentScenario.*
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stonetree.imdbnews.MainView
import com.stonetree.imdbnews.core.constants.Endpoint
import com.stonetree.imdbnews.core.mocks.IMDBMocks
import com.stonetree.imdbnews.feature.latest.view.LatestView
import com.stonetree.restclient.feature.repository.RestClient
import com.stonetree.restclient.feature.repository.RestClientImpl
import junit.framework.Assert.assertEquals
import kotlinx.android.synthetic.main.view_latest.*
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import okhttp3.mockwebserver.MockResponse
import org.junit.Test
import org.junit.runners.JUnit4
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.robolectric.annotation.Config

class LatestViewTest : AutoCloseKoinTest() {

    private lateinit var activity: MainView

    private val server = MockWebServer()

    private val module = module {
        factory<RestClient> { RestClientImpl(get()) }
    }

    private val rest: RestClient by inject<RestClientImpl> {
        parametersOf("http://10.0.0.2:8080/")
    }

    @Before
    fun setup() {
        startKoin { loadKoinModules(module) }

        val response = MockResponse()
            .setResponseCode(200)
            .setBody(IMDBMocks.latest)

        server.start()
        server.url(Endpoint.LATEST_PATH)
        server.enqueue(response)

        ActivityScenario.launch(MainView::class.java).use { scenario ->
            scenario.onActivity { activity ->
                this.activity = activity
            }
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun test() {
        activity.latest.findViewHolderForAdapterPosition(0)?.apply {
            assertEquals(475557, this.itemView.tag as Long)
        }
    }
}