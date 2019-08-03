package com.stonetree.shuttergallery.feature.shutter.res.repository

import android.util.Log
import com.stonetree.corerepository.extensions.enqueue
import com.stonetree.corerepository.feature.CoreRepository
import com.stonetree.shuttergallery.feature.shutter.model.Image
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response

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
                    val images: List<Image> = parse(response)
                    images.forEach { image ->
                        Log.i(javaClass.name, image.assets.thumb.url)
                    }
                }

                onFailure = { error ->
                    Log.e(javaClass.name, error?.let { it.message })
                }
            }
        }
    }

    private fun parse(response: Response<ShutterModel>) : List<Image>
    {
        val list = arrayListOf<Image>()
        response.body()?.data?.forEach { image ->
            val row = Image()
            row.id = image.id
            row.aspect = image.aspect
            row.assets = image.assets
            list.add(row)
        }
        return list
    }
}
