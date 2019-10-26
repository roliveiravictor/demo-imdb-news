package com.stonetree.imdbnews.feature.details.res.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.stonetree.imdbnews.core.repository.MainRepository
import com.stonetree.restclient.core.extensions.enqueue
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.feature.RestClient
import com.stonetree.imdbnews.feature.details.model.DetailsModel
import com.stonetree.imdbnews.feature.details.res.api.DetailsApi
import com.stonetree.imdbnews.feature.details.view.DetailsViewArgs
import com.stonetree.restclient.feature.RestClientCallback
import retrofit2.Call

class DetailsRepository(private val client: RestClient) : MainRepository() {

    val api: DetailsApi = client.generate(DetailsApi::class)

    var network = MutableLiveData<NetworkState>()
        private set

    var details = MutableLiveData<DetailsModel>()
    private set

    private lateinit var request: Call<DetailsModel>

    override fun retry() {
        request.clone().enqueue(network) {
            executeGet(this)
        }
    }

    fun get(args: DetailsViewArgs) {
        request = api.get(args.movieId, client.key())
        request.enqueue(network) {
            executeGet(this)
        }
    }

    private fun executeGet(request: RestClientCallback<DetailsModel>) {
        request.onResponse = { response ->
            response.body()?.let { details ->
                this@DetailsRepository.details.postValue(details)
            }
        }

        request.onFailure = { error ->
            error?.apply {
                Log.e(javaClass.name, message.toString())
            }
        }
    }
}
