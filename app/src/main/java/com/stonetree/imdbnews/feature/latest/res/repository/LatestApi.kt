package com.stonetree.imdbnews.feature.latest.res.repository

import com.stonetree.restclient.core.constants.RepositoryConstants.API_KEY
import com.stonetree.restclient.core.constants.RepositoryConstants.PAGE
import com.stonetree.imdbnews.core.constants.Endpoint.LATEST_PATH
import com.stonetree.imdbnews.feature.latest.model.LatestModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LatestApi {

    @GET(LATEST_PATH)
    fun get(
        @Query(PAGE) page: Long,
        @Query(API_KEY) key: String
    ): Call<LatestModel>
}
