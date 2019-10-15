package com.stonetree.imdbnews.feature.latest.res.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import com.stonetree.imdbnews.feature.latest.model.Movie
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository

class LatestDataSource(
    private val repository: LatestRepository
) : PageKeyedDataSource<Long, Movie>() {

    var network = MutableLiveData<NetworkState>()
        private set

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Movie>
    ) {
        network.postValue(NetworkState.LOADING)
        repository.load {
            callback.onResult(this, null, 2L)
            network.postValue(NetworkState.LOADED)
        }
    }

    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, Movie>
    ) {
        repository.lazy(params) { movies ->
            callback.onResult(movies, getNextKey(this, params))
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        Log.w(javaClass.name, params.key.toString())
    }

    private fun getNextKey(model: LatestModel, params: LoadParams<Long>): Long? {
        return if (model.totalPages == params.key)
            null
        else
            params.key + 1
    }
}
