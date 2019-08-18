package com.stonetree.imdbnews.feature.details.model

import junit.framework.TestCase.assertNull
import org.junit.Test

class DetailsModelTest {

    @Test
    fun test_detailsModel_shouldReturnDefaultValues() {
        DetailsModel().apply {
            assertNull(title)
            assertNull(description)
            assertNull(poster)
        }
    }
}
