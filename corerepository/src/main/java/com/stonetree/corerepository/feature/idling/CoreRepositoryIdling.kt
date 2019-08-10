package com.stonetree.corerepository.feature.idling

object CoreRepositoryIdling {

    private const val resource = "GLOBAL"

    private val countingIdlingResource = CoreRepositoryIdlingImp(resource)

    fun getResource(): CoreRepositoryIdlingImp = countingIdlingResource
}
