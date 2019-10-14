package com.stonetree.imdbnews.core.injector

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class Injector {

    private val coreRepository = module {

    }

    fun startModules(): List<Module> {
        return arrayListOf(coreRepository)
    }
}
