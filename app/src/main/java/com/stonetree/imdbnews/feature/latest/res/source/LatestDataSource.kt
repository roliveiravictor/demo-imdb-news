package com.stonetree.imdbnews.feature.latest.res.source

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.stonetree.corerepository.core.extensions.enqueue
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.corerepository.core.model.NetworkState.Companion.LOADED
import com.stonetree.corerepository.core.model.NetworkState.Companion.LOADING
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import com.stonetree.imdbnews.feature.latest.model.Movie
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
import retrofit2.Call

class LatestDataSource
    (private val repository: LatestRepository) : PageKeyedDataSource<Long, Movie>() {

    private val network = MutableLiveData<NetworkState>()

    fun getNetwork(): MutableLiveData<NetworkState> {
        return network
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Movie>
    ) {

        network.postValue(LOADING)
        val request: Call<LatestModel> = repository.api.get(1, repository.repository.key())
        request.enqueue {
            onResponse = { response ->
                response.body()?.results?.let { movies ->
                    callback.onResult(movies, null, 2L)
                    network.postValue(LOADED)
                }
            }

            onFailure = { error ->
                network.postValue(NetworkState.error(error?.message))
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        val request: Call<LatestModel> = repository.api.get(params.key, repository.repository.key())
        request.enqueue {
            onResponse = { response ->
                response.body()?.apply {
                    results?.let { movies ->
                        callback.onResult(movies, getNextKey(this, params))
                    }
                }
            }

            onFailure = { error ->
                network.postValue(NetworkState.error(error?.message))
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Movie>) {
        Log.w(javaClass.name, params.key.toString())
    }

    @VisibleForTesting(otherwise = PRIVATE)
    fun getNextKey(model: LatestModel, params: LoadParams<Long>): Long? {
        return if (model.totalPages == params.key)
            null
        else
            params.key + 1
    }
}
