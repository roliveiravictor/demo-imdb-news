package com.stonetree.imdbnews.core.constants

import com.stonetree.imdbnews.core.constants.DirectionsBundleKey.MOVIE_ID

object Endpoint {
    const val LATEST_PATH = "movie/now_playing"
    const val DETAILS_PATH = "movie/{$MOVIE_ID}"
}
