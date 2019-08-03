package com.stonetree.shuttergallery.feature.shutter.res.factory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.stonetree.shuttergallery.feature.shutter.model.Image
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel
import com.stonetree.shuttergallery.feature.shutter.res.repository.ShutterRepository
import com.stonetree.shuttergallery.feature.shutter.res.source.ShutterDataSource

class ShutterDataSourceFactory(private val repository: ShutterRepository)
    : DataSource.Factory<Int, Image>()
{
    private val data = MutableLiveData<ShutterDataSource>()

    private var source: ShutterDataSource? = null

    override fun create(): DataSource<Int, Image> {
        source = ShutterDataSource(repository)
        data.postValue(source)
        return source as ShutterDataSource
    }
}