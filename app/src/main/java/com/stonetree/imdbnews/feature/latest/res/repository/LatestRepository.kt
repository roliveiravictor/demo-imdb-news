package com.stonetree.imdbnews.feature.latest.res.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PageKeyedDataSource.*
import com.stonetree.corerepository.core.extensions.enqueue
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.corerepository.feature.callback.CoreRepositoryCallback
import com.stonetree.corerepository.feature.repository.CoreRepository
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import com.stonetree.imdbnews.feature.latest.model.Movie
import retrofit2.Call

class LatestRepository(val core: CoreRepository) {

    private val api: LatestApi = core.create(LatestApi::class)

    fun load(callback: List<Movie>.() -> Unit) {
        val request: Call<LatestModel> = api.get(1, core.key())
        request.enqueue {
            onResponse = { response ->
                response.body()?.results?.let { movies ->
                    callback.invoke(movies)
                }
            }

            onFailure = { error ->
                //                network.postValue(NetworkState.error(error?.message))
            }
        }
    }

    fun lazy(params: LoadParams<Long>, callback: LatestModel.(List<Movie>) -> Unit) {
        val request: Call<LatestModel> = api.get(params.key, core.key())
        request.enqueue {
            onResponse = { response ->
                response.body()?.apply {
                    results?.let { movies ->
                        callback.invoke(this, movies)
                    }
                }
            }

            onFailure = { error ->
                //                network.postValue(NetworkState.error(error?.message))
            }
        }
    }
}

