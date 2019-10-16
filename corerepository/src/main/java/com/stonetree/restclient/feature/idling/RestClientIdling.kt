package com.stonetree.restclient.feature.idling

object RestClientIdling {

    private const val resource = "GLOBAL"

    private val countingIdlingResource = RestClientIdlingImp(resource)

    fun getResource(): RestClientIdlingImp = countingIdlingResource
}
