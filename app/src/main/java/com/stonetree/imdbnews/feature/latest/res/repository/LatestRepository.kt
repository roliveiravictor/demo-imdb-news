package com.stonetree.imdbnews.feature.latest.res.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource.*
import com.stonetree.restclient.core.extensions.enqueue
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.feature.RestClient
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import com.stonetree.imdbnews.feature.latest.model.Movie
import retrofit2.Call

class LatestRepository(val repository: RestClient) {

    private val api: LatestApi = repository.generate(LatestApi::class)

    private val network = MutableLiveData<NetworkState>()

    fun getNetwork(): MutableLiveData<NetworkState> {
        return network
    }

    fun load(callback: List<Movie>.() -> Unit) {
        network.postValue(NetworkState.LOADING)

        val request: Call<LatestModel> = api.get(1, repository.key())
        request.enqueue {
            onResponse = { response ->
                response.body()?.results?.let { movies ->
                    callback.invoke(movies)
                    network.postValue(NetworkState.LOADED)
                }
            }

            onFailure = { error ->
                network.postValue(NetworkState.error(error?.message))
            }
        }
    }

    fun lazy(params: LoadParams<Long>, callback: LatestModel.(List<Movie>) -> Unit) {
        val request: Call<LatestModel> = api.get(params.key, repository.key())
        request.enqueue {
            onResponse = { response ->
                response.body()?.apply {
                    results?.let { movies ->
                        callback.invoke(this, movies)
                    }
                }
            }

            onFailure = { error ->
                network.postValue(NetworkState.error(error?.message))
            }
        }
    }
}

