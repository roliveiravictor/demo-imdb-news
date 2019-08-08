package com.stonetree.corerepository.feature.callback

import com.stonetree.corerepository.feature.idling.CoreRepositoryIdling
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoreRepositoryCallback<T>: Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
        CoreRepositoryIdling.getResource().decrement()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
        CoreRepositoryIdling.getResource().decrement()
    }
}