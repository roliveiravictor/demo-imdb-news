package com.stonetree.shuttergallery.feature.shutter.model

import com.google.gson.annotations.SerializedName

class ShutterModel {

    var page: Long = -1

    @SerializedName("per_page")
    var perPage: Int = -1

    @SerializedName("total_count")
    var totalCount: Long = -1

    @SerializedName("search_id")
    var searchId: String = ""

    lateinit var data: List<Image>
}