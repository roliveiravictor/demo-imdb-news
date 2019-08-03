package com.stonetree.shuttergallery.feature.shutter.res.repository

import com.stonetree.shuttergallery.constants.Endpoint
import com.stonetree.shuttergallery.feature.shutter.model.ShutterModel
import retrofit2.Call
import retrofit2.http.GET

interface ShutterApi {

    @GET(Endpoint.IMAGES_PATH)
    fun get(): Call<ShutterModel>

}