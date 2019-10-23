package com.stonetree.imdbnews.feature.details.res.api

import com.stonetree.restclient.core.constants.RestclientConstants.API_KEY
import com.stonetree.imdbnews.core.constants.DirectionsBundleKey.MOVIE_ID
import com.stonetree.imdbnews.core.constants.Endpoint.DETAILS_PATH
import com.stonetree.imdbnews.feature.details.model.DetailsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailsApi {

    @GET(DETAILS_PATH)
    fun get(
        @Path(MOVIE_ID) id: Long,
        @Query(API_KEY) key: String
    ): Call<DetailsModel>
}
