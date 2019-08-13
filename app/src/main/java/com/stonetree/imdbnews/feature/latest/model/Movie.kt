package com.stonetree.imdbnews.feature.latest.model

import com.google.gson.annotations.SerializedName

class Movie {

    var id: Long? = null

    @SerializedName("poster_path")
    var poster: String? = null
}