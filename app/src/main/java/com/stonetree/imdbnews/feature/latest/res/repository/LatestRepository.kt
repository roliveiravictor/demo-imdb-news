package com.stonetree.imdbnews.feature.latest.res.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource.*
import com.stonetree.imdbnews.core.repository.MainRepository
import com.stonetree.restclient.core.extensions.enqueue
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.feature.RestClient
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import com.stonetree.imdbnews.feature.latest.model.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call

class LatestRepository(val client: RestClient) : MainRepository() {

    private val api: LatestApi = client.generate(LatestApi::class)

    var network = MutableLiveData<NetworkState>()
        private set

    private lateinit var request: Call<LatestModel>

    fun load(callback: List<Movie>.() -> Unit) {
        request = api.get(1, client.key())
        request.enqueue(network) {
            onResponse = { response ->
                response.body()?.results?.let { movies ->
                    callback.invoke(movies)
                }
            }

            onFailure = { error ->
                error?.apply {
                    Log.e(javaClass.name, message.toString())
                }
            }
        }
    }

    fun lazy(params: LoadParams<Long>, callback: LatestModel.(List<Movie>) -> Unit) {
        request = api.get(params.key, client.key())
        request.enqueue(network) {
            onResponse = { response ->
                response.body()?.apply {
                    results?.let { movies ->
                        callback.invoke(this, movies)
                    }
                }
            }

            onFailure = { error ->
                error?.apply {
                    Log.e(javaClass.name, message.toString())
                }
            }
        }
    }
}

