package com.stonetree.imdbnews.core.utils

import com.stonetree.imdbnews.feature.details.res.factory.DetailsViewModelFactory
import com.stonetree.imdbnews.feature.details.view.DetailsViewArgs
import com.stonetree.imdbnews.feature.latest.res.factory.LatestViewModelFactory
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Test
import org.mockito.Mockito.mock

class InjectorUtilsTest {

    @Test
    fun test_injectorUtils_shouldReturnDefaultValues() {
        val latestFactory = InjectorUtils.provideLatestViewModelFactory()
        assertThat(latestFactory, `is`(any(LatestViewModelFactory::class.java)))

        val args = mock(DetailsViewArgs::class.java)
        val detailsFactory = InjectorUtils.provideDetailsViewModelFactory(args)
        assertThat(detailsFactory, `is`(any(DetailsViewModelFactory::class.java)))
    }
}