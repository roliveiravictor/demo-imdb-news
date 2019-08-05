package com.stonetree.corerepository.feature.res.callback

import com.stonetree.corerepository.core.idling.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallBackKt<T>: Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
        EspressoIdlingResource.getIdlingResource().decrement()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
        EspressoIdlingResource.getIdlingResource().decrement()
    }
}