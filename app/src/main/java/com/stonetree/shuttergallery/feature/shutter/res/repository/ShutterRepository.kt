package com.stonetree.shuttergallery.feature.shutter.res.repository

import android.util.Log
import com.stonetree.corerepository.extensions.enqueue
import com.stonetree.corerepository.feature.CoreRepository
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

class ShutterRepository {

    companion object {
        @Volatile
        private var instance: ShutterRepository? = null

        fun getInstance() = instance
            ?: synchronized(this) {
            ShutterRepository()
                .also { repository ->
                instance = repository
            }
        }
    }

    suspend fun fetch() {
        val api = CoreRepository
            .getInstance()
            .retrofit
            .create(ShutterApi::class.java)

        val request: Call<ShutterModel> = api.get()
        withContext(Dispatchers.IO) {
            request.enqueue {
                onResponse = { response ->
                    Log.i(javaClass.name, response.body()?.url)
                }

                onFailure = { error ->
                    Log.e(javaClass.name, error?.message)
                }
            }
        }
    }
}
