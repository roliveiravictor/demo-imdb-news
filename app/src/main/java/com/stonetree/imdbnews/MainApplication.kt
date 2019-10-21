package com.stonetree.imdbnews

import android.app.Application
import com.stonetree.restclient.feature.repository.RestClient
import com.stonetree.imdbnews.core.injector.Injector
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainApplication : Application() {

    private val repository: RestClient by inject()

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            Injector().apply {
                loadKoinModules(startModules())
            }
        }

        repository.start(this@MainApplication)
    }
}
