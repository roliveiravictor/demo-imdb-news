package com.stonetree.imdbnews.feature.latest.res.directions

import android.os.Bundle
import androidx.navigation.NavDirections
import com.stonetree.imdbnews.R
import com.stonetree.imdbnews.core.constants.DirectionsBundleKey.MOVIE_ID

class LatestDirections private constructor() {

    private data class ActionLatestToDetails(val id: Long) : NavDirections {
        override fun getActionId(): Int = R.id.details_view

        override fun getArguments(): Bundle {
            val result = Bundle()
            result.putLong(MOVIE_ID, this.id)
            return result
        }
    }

    companion object {
        fun actionLatestToDetails(id: Long): NavDirections = ActionLatestToDetails(id)
    }
}
