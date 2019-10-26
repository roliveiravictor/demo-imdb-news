package com.stonetree.restclient.core.extensions

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.stonetree.restclient.core.model.NetworkState
import com.stonetree.restclient.feature.idling.RestClientIdling
import com.stonetree.restclient.feature.RestClientCallback
import retrofit2.Call
import java.util.Properties

fun <T> Call<T>.enqueue(
    network: MutableLiveData<NetworkState>,
    callback: RestClientCallback<T>.() -> Unit
) {
    RestClientIdling.getResource().increment()
    network.apply {
        postValue(NetworkState.LOADING)
        RestClientCallback<T>(network).also { core->
            callback.invoke(core)
            enqueue(core)
        }
    }
}

// TODO - Remove security breach.
fun String.read(context: Context, name: String): String {
    return context.assets.open(this).use { input ->
        val props = Properties()
        props.load(input)
        props.getProperty(name)
    }
}
