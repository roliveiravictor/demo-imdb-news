package com.stonetree.shuttergallery.feature.shutter.res.repository

import com.stonetree.corerepository.feature.RepositoryConstants.PAGE
import com.stonetree.corerepository.feature.RepositoryConstants.PAGE_SIZE
import com.stonetree.corerepository.feature.RepositoryConstants.PER_PAGE
import com.stonetree.shuttergallery.constants.Endpoint
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ShutterApi {

    @GET(Endpoint.IMAGES_PATH)
    fun get(
        @Query(PAGE) page: Long,
        @Query(PER_PAGE) perPage: Int = PAGE_SIZE
    ): Call<ShutterModel>
}