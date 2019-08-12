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
import com.stonetree.imdbnews.feature.latest.model.Image
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import com.stonetree.imdbnews.feature.latest.res.repository.LatestRepository
import retrofit2.Call

class LatestDataSource: PageKeyedDataSource<Long, Image>() {

    private val network = MutableLiveData<NetworkState>()

    private val repository: LatestRepository = LatestRepository.getInstance()

    fun getNetwork(): MutableLiveData<NetworkState> {
        return network
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Image>
    ) {
        network.postValue(LOADING)
        val request: Call<LatestModel> = repository.api.get(1)
        request.enqueue {
            onResponse = { response ->
                response.body()?.data?.let { images ->
                    callback.onResult(images, null, 2L)
                    network.postValue(LOADED)
                }
            }

            onFailure = { error ->
                network.postValue(NetworkState.error(error?.let { it.message }))
            }
        }
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Image>) {
        val request: Call<LatestModel> = repository.api.get(params.key)
        request.enqueue {
            onResponse = { response ->
                response.body()?.apply {
                    callback.onResult(data, getNextKey(this, params))
                }
            }

            onFailure = { error ->
                network.postValue(NetworkState.error(error?.let { it.message }))
            }
        }
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Image>) {
        Log.w(javaClass.name, params.key.toString())
    }

    @VisibleForTesting(otherwise = PRIVATE)
    fun getNextKey(
        model: LatestModel,
        params: LoadParams<Long>
    ): Long? {
        return if(model.totalCount == params.key)
                null
            else
                params.key + 1
    }
}
