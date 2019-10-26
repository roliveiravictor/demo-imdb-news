package com.stonetree.restclient.feature

import androidx.lifecycle.MutableLiveData
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.feature.idling.RestClientIdling
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestClientCallback<T>(private val network: MutableLiveData<NetworkState>) : Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        network.postValue(NetworkState.error(t.message))
        onFailure?.invoke(t)
        RestClientIdling.getResource().decrement()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        network.postValue(NetworkState.LOADED)
        onResponse?.invoke(response)
        RestClientIdling.getResource().decrement()
    }
}
