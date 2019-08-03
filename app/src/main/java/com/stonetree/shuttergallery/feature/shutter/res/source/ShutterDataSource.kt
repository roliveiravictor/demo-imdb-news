package com.stonetree.shuttergallery.feature.shutter.res.source

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel
import com.stonetree.shuttergallery.feature.shutter.res.repository.ShutterRepository

class ShutterDataSource(private val repository: ShutterRepository)
    : PageKeyedDataSource<Int, ShutterModel>()
{
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ShutterModel>
    ) {
        Log.w(javaClass.name, "initial")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ShutterModel>) {
        Log.w(javaClass.name, "after")
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ShutterModel>) {
        Log.w(javaClass.name, "before")
    }
}
