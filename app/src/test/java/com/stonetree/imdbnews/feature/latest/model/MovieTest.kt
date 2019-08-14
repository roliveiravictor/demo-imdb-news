package com.stonetree.imdbnews.feature.latest.model

import junit.framework.TestCase.*
import org.junit.Test

class MovieTest {

    @Test
    fun test_latestModel_shouldReturnDefaultValues() {
        val movie = Movie()
        assertNull(movie.id)
        assertNull(movie.poster)
    }
}