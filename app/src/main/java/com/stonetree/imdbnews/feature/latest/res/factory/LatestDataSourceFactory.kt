package com.stonetree.imdbnews.feature.latest.res.factory

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.stonetree.imdbnews.feature.latest.model.Image
import com.stonetree.imdbnews.feature.latest.res.source.LatestDataSource

class LatestDataSourceFactory
    : DataSource.Factory<Long, Image>()
{
    val data = MutableLiveData<LatestDataSource>()

    @VisibleForTesting(otherwise = PRIVATE)
    var source: LatestDataSource? = null

    override fun create(): DataSource<Long, Image> {
        source = LatestDataSource()
        data.postValue(source)
        return source as LatestDataSource
    }
}
