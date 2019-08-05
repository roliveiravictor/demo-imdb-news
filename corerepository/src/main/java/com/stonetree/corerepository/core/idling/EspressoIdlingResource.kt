package com.stonetree.corerepository.core.idling

import com.stonetree.corerepository.feature.res.idling.GlobalIdlingResource

object EspressoIdlingResource {

    private const val resource = "GLOBAL"

    private val countingIdlingResource = GlobalIdlingResource(resource)

    fun getIdlingResource(): GlobalIdlingResource =
        countingIdlingResource
}