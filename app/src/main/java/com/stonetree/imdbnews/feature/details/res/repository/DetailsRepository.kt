package com.stonetree.imdbnews.feature.details.res.repository

import androidx.lifecycle.MutableLiveData
import com.stonetree.restclient.core.extensions.enqueue
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.feature.repository.RestClient
import com.stonetree.imdbnews.feature.details.model.DetailsModel
import com.stonetree.imdbnews.feature.details.res.api.DetailsApi
import com.stonetree.imdbnews.feature.details.view.DetailsViewArgs
import retrofit2.Call

class DetailsRepository(private val repository: RestClient) {

    val api: DetailsApi = repository.create(DetailsApi::class)

    var network = MutableLiveData<NetworkState>()
    private set

    private val details = MutableLiveData<DetailsModel>()

    fun details(): MutableLiveData<DetailsModel> {
        return details
    }

    fun get(args: DetailsViewArgs) {
        network.postValue(NetworkState.LOADING)
        val request: Call<DetailsModel> = api.get(args.movieId, repository.key())
        request.enqueue {
            onResponse = { response ->
                response.body()?.let { details ->
                    this@DetailsRepository.details.postValue(details)
                    network.postValue(NetworkState.LOADED)
                }
            }

            onFailure = { error ->
                network.postValue(NetworkState.error(error?.let { it.message }))
            }
        }
    }
}
