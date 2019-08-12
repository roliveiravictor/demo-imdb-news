package com.stonetree.imdbnews.feature.latest.model

import com.google.gson.annotations.SerializedName

class LatestModel {

    var page: Long = -1

    @SerializedName("per_page")
    var perPage: Int = -1

    @SerializedName("total_count")
    var totalCount: Long = -1

    @SerializedName("search_id")
    var searchId: String = ""

    lateinit var data: List<Image>
}
