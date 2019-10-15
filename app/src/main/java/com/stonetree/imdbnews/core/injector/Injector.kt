package com.stonetree.imdbnews.core.injector

import com.stonetree.corerepository.feature.repository.CoreRepository
import com.stonetree.corerepository.feature.repository.CoreRepositoryImpl
import com.stonetree.imdbnews.feature.latest.res.factory.LatestDataSourceFactory
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
import com.stonetree.imdbnews.feature.latest.res.source.LatestDataSource
import com.stonetree.imdbnews.feature.latest.view.adapter.LatestAdapter
import com.stonetree.imdbnews.feature.latest.viewmodel.LatestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import java.lang.reflect.Array.get

class Injector {

    private val latest = module {
        factory { LatestAdapter() }
        factory { LatestRepository(get()) }
        factory { LatestDataSource(get()) }
        factory { LatestDataSourceFactory(get(), get()) }
        viewModel { LatestViewModel(get(), get()) }
    }

    private val coreRepository = module {
        single<CoreRepository> { CoreRepositoryImpl() }
    }

    fun startModules(): List<Module> {
        return arrayListOf(coreRepository, latest)
    }
}
