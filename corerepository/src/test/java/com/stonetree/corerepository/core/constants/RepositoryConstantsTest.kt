package com.stonetree.corerepository.core.constants

import com.stonetree.corerepository.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PASSWORD
import com.stonetree.corerepository.core.constants.RepositoryConstants.PER_PAGE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PRE_FETCH_DISTANCE
import com.stonetree.corerepository.core.constants.RepositoryConstants.REPOSITORY_PROPS
import com.stonetree.corerepository.core.constants.RepositoryConstants.TIMEOUT
import com.stonetree.corerepository.core.constants.RepositoryConstants.USERNAME
import junit.framework.TestCase.assertEquals
import org.junit.Test

class RepositoryConstantsTest {

    @Test
    fun test_imagesPath_shouldReturnSame() {
        assertEquals("baseUrl", BASE_URL)
        assertEquals("username", USERNAME)
        assertEquals("password", PASSWORD)
        assertEquals("repository.properties", REPOSITORY_PROPS)
        assertEquals("page", PAGE)
        assertEquals("per_page", PER_PAGE)
        assertEquals(5, TIMEOUT)
        assertEquals(15, PAGE_SIZE)
        assertEquals(10, PRE_FETCH_DISTANCE)
    }
}
