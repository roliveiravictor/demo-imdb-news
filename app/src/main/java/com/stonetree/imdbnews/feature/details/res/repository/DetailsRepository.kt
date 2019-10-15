package com.stonetree.imdbnews.feature.details.res.repository

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.stonetree.corerepository.core.extensions.enqueue
import com.stonetree.corerepository.core.model.NetworkState
import com.stonetree.corerepository.feature.repository.CoreRepository
import com.stonetree.corerepository.feature.repository.CoreRepositoryImpl
import com.stonetree.imdbnews.feature.details.model.DetailsModel
import com.stonetree.imdbnews.feature.details.res.api.DetailsApi
import com.stonetree.imdbnews.feature.details.view.DetailsViewArgs
import retrofit2.Call
import java.lang.reflect.Modifier.PRIVATE

class DetailsRepository(private val repository: CoreRepository) {

    val api: DetailsApi = repository.create(DetailsApi::class)

    private val network = MutableLiveData<NetworkState>()

    private val details = MutableLiveData<DetailsModel>()

    fun getNetwork(): MutableLiveData<NetworkState> {
        return network
    }

    fun getDetails(): MutableLiveData<DetailsModel> {
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
