package com.stonetree.imdbnews.core.utils

import com.stonetree.imdbnews.feature.details.res.factory.DetailsViewModelFactory
import com.stonetree.imdbnews.feature.latest.res.factory.LatestViewModelFactory

object InjectorUtils {

    fun provideLatestViewModelFactory(): LatestViewModelFactory {
        return LatestViewModelFactory()
    }

    fun provideDetailsViewModelFactory(): DetailsViewModelFactory {
        return DetailsViewModelFactory()
    }
}