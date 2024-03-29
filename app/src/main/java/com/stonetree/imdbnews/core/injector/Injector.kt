package com.stonetree.imdbnews.core.injector

import com.stonetree.restclient.feature.httpclient.CoreHttpClient
import com.stonetree.restclient.feature.httpclient.CoreHttpClientImpl
import com.stonetree.restclient.feature.interceptor.RestClientInterceptor
import com.stonetree.restclient.feature.interceptor.RestClientInterceptorImpl
import com.stonetree.restclient.feature.RestClient
import com.stonetree.restclient.feature.RestClientImpl
import com.stonetree.imdbnews.feature.details.res.repository.DetailsRepository
import com.stonetree.imdbnews.feature.details.view.DetailsViewArgs
import com.stonetree.imdbnews.feature.details.viewmodel.DetailsViewModel
import com.stonetree.imdbnews.feature.latest.res.factory.LatestDataSourceFactory
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
import com.stonetree.imdbnews.feature.latest.res.source.LatestDataSource
import com.stonetree.imdbnews.feature.latest.view.adapter.LatestAdapter
import com.stonetree.imdbnews.feature.latest.viewmodel.LatestViewModel
import com.stonetree.restclient.feature.network.NetworkChangeReceiverImpl
import com.stonetree.restclient.feature.network.NetworkReceiver
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class Injector {

    private val details = module {
        factory { DetailsRepository(get()) }
        viewModel { (args: DetailsViewArgs) -> DetailsViewModel(get(), args) }
    }

    private val latest = module {
        factory { LatestAdapter() }
        factory { LatestDataSource(get()) }
        factory { LatestDataSourceFactory(get()) }

        single { LatestRepository(get()) }

        viewModel { LatestViewModel(get(), get()) }
    }

    private val rest = module {
        factory<RestClientInterceptor> { RestClientInterceptorImpl() }
        factory<CoreHttpClient> { CoreHttpClientImpl(get()) }
        single<RestClient> { RestClientImpl() }
        single<NetworkReceiver> { NetworkChangeReceiverImpl() }
    }

    fun startModules(): List<Module> {
        return arrayListOf(rest, latest, details)
    }
}
