package com.stonetree.imdbnews.feature.latest.res.repository

import com.stonetree.corerepository.feature.repository.CoreRepository

class LatestRepository(val repository: CoreRepository) {

    val api: LatestApi = repository.create(LatestApi::class)

}
