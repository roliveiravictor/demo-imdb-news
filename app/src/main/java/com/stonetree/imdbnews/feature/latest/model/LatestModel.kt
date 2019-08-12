package com.stonetree.imdbnews.feature.latest.model

import com.google.gson.annotations.SerializedName

class LatestModel {

    var page: Long? = null

    @SerializedName("total_pages")
    var totalPages: Long? = null

    var results: List<Movie>? = null
}
