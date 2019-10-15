package com.stonetree.imdbnews.feature.latest.res.factory

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.stonetree.imdbnews.feature.latest.model.Movie
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
import com.stonetree.imdbnews.feature.latest.res.source.LatestDataSource
import org.koin.core.parameter.parametersOf

class LatestDataSourceFactory(
    val repository: LatestRepository,
    val source: LatestDataSource
) : DataSource.Factory<Long, Movie>() {

    val data = MutableLiveData<LatestDataSource>()

    override fun create(): DataSource<Long, Movie> {
        data.postValue(source)
        return source
    }
}
