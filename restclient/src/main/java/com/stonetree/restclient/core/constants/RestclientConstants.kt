package com.stonetree.restclient.core.constants

object RestclientConstants {

    const val BASE_URL: String = "base_url"
    const val POSTER_URL: String = "poster_url"

    const val API_KEY: String = "api_key"

    const val REPOSITORY_PROPS: String = "repository.properties"
    const val TEST_PROPS: String = "test.properties"

    const val PAGE: String = "page"

    const val TIMEOUT: Long = 5
    const val PAGE_SIZE: Int = 15
    const val PRE_FETCH_DISTANCE: Int = 10
    const val MAX_THREADS: Int = 3

    object ACTIONS {

        const val NETWORK_ERROR = "com.stonetree.restclient.feature.error.NetworkErrorActivity"
    }
}
