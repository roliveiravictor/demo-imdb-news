package com.stonetree.imdbnews.feature.details.model

import com.google.gson.annotations.SerializedName

class DetailsModel {

    @SerializedName("original_title")
    var title: String? = null

    @SerializedName("overview")
    var description: String? = null

    @SerializedName("poster_path")
    var poster: String? = null
}
