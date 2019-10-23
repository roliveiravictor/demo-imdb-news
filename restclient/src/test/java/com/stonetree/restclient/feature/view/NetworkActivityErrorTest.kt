package com.stonetree.restclient.feature.view

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import com.stonetree.restclient.feature.network.NetworkChangeReceiverImpl
import com.stonetree.restclient.feature.network.NetworkReceiver
import junit.framework.TestCase.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.*

@RunWith(RobolectricTestRunner::class)
class NetworkActivityErrorTest : AutoCloseKoinTest() {

    private val module = module {
        factory<NetworkReceiver> { NetworkChangeReceiverImpl() }
    }

    private val receiver: NetworkReceiver by inject()

    private lateinit var activity: NetworkErrorActivity

    private val OFFLINE_MESSAGE = "Offline Message."

    private val OFFLINE_ACTION = "com.stonetree.restclient.feature.error.NetworkErrorActivity"
    private val ONLINE__ACTION = "com.stonetree.imdbnews.NavigatorActivity"

    @Before
    fun setup() {
        ActivityScenario.launch(NetworkErrorActivity::class.java).use { scenario ->
            scenario.onActivity { activity ->
                this.activity = activity
            }
        }
        startKoin {
            loadKoinModules(module)
        }
        receiver.registerOfflineIntent(
            OFFLINE_ACTION,
            OFFLINE_MESSAGE
        )
        receiver.registerOnlineIntent(ONLINE__ACTION)
    }

    @Test
    @Ignore
        /** https://github.com/robolectric/robolectric/pull/4736 **/
    fun onConnectionOnline_shouldReturnNetworkErrorActivity() {
        val shadowIntent = shadowOf(activity).nextStartedActivity
        val galleryIntent = Intent(OFFLINE_ACTION)
        assertTrue(galleryIntent.filterEquals(shadowIntent))
    }
}