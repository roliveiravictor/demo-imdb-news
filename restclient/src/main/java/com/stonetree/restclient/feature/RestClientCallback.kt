package com.stonetree.restclient.feature

import com.stonetree.restclient.feature.idling.RestClientIdling
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestClientCallback<T> : Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
        RestClientIdling.getResource().decrement()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
        RestClientIdling.getResource().decrement()
    }
}
