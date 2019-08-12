package com.stonetree.imdbnews.feature.latest.res.repository

import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PER_PAGE
import com.stonetree.imdbnews.core.constants.Endpoint
import com.stonetree.imdbnews.core.constants.Endpoint.LATEST_PATH
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LatestApi {

    @GET(LATEST_PATH)
    fun get(
        @Query(PAGE) page: Long,
        @Query(PER_PAGE) perPage: Int = PAGE_SIZE
    ): Call<LatestModel>
}
