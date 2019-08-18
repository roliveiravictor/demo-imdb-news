package com.stonetree.imdbnews.core.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stonetree.imdbnews.feature.details.res.factory.DetailsViewModelFactory
import com.stonetree.imdbnews.feature.details.view.DetailsViewArgs
import com.stonetree.imdbnews.feature.latest.res.factory.LatestViewModelFactory
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
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