package com.stonetree.imdbnews.feature.latest.res.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.stonetree.imdbnews.feature.latest.model.Movie
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
import com.stonetree.imdbnews.feature.latest.res.source.LatestDataSource

class LatestDataSourceFactory(
    val repository: LatestRepository
) : DataSource.Factory<Long, Movie>() {

    val data = MutableLiveData<LatestDataSource>()

    override fun create(): DataSource<Long, Movie> {
        return LatestDataSource(repository).also { source ->
            data.postValue(source)
        }
    }
}
