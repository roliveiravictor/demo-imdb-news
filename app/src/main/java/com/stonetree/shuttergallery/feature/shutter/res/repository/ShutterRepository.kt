package com.stonetree.shuttergallery.feature.shutter.res.repository

import com.stonetree.corerepository.feature.repository.CoreRepository

class ShutterRepository {

    val api = CoreRepository
        .getInstance()
        .retrofit
        .create(ShutterApi::class.java)

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
}
