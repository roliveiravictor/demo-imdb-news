package com.stonetree.corerepository.core.constants

import com.stonetree.corerepository.core.constants.RepositoryConstants.BASE_URL
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE
import com.stonetree.corerepository.core.constants.RepositoryConstants.PAGE_SIZE
import com.stonetree.corerepository.core.constants.RepositoryConstants.API_KEY
import com.stonetree.corerepository.core.constants.RepositoryConstants.MAX_THREADS
import com.stonetree.corerepository.core.constants.RepositoryConstants.POSTER_URL
import com.stonetree.corerepository.core.constants.RepositoryConstants.PRE_FETCH_DISTANCE
import com.stonetree.corerepository.core.constants.RepositoryConstants.REPOSITORY_PROPS
import com.stonetree.corerepository.core.constants.RepositoryConstants.TEST_PROPS
import com.stonetree.corerepository.core.constants.RepositoryConstants.TIMEOUT
import junit.framework.TestCase.assertEquals
import org.junit.Test

class RepositoryConstantsTest {

    @Test
    fun test_imagesPath_shouldReturnSame() {
        assertEquals("base_url", BASE_URL)
        assertEquals("poster_url", POSTER_URL)
        assertEquals("api_key", API_KEY)
        assertEquals("repository.properties", REPOSITORY_PROPS)
        assertEquals("test.properties", TEST_PROPS)
        assertEquals("page", PAGE)
        assertEquals(5, TIMEOUT)
        assertEquals(15, PAGE_SIZE)
        assertEquals(10, PRE_FETCH_DISTANCE)
        assertEquals(3, MAX_THREADS)
    }
}
