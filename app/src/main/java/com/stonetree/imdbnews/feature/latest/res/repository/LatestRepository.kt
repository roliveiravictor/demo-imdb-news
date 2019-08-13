package com.stonetree.imdbnews.feature.latest.res.repository

import com.stonetree.corerepository.feature.repository.CoreRepository

class LatestRepository {

    val api: LatestApi = CoreRepository
        .getInstance()
        .retrofit
        .create(LatestApi::class.java)

    companion object {
        @Volatile
        private var instance: LatestRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            LatestRepository().also { repository ->
                instance = repository
            }
        }
    }
}
