package dev.mcd.redditsortof.domain.reddit.listing

import dev.mcd.redditsortof.data.reddit.api.ListingApiClient
import dev.mcd.redditsortof.data.reddit.api.ListingApiClient.Result
import dev.mcd.redditsortof.data.reddit.api.ListingApiClient.Result.HttpError
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetHotLinksTest {

    @MockK
    lateinit var listingApiClient: ListingApiClient

    @InjectMockKs
    lateinit var subject: GetHotLinksImpl

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `Given an error response, When executed, Then return error`() = runBlocking {
        coEvery { listingApiClient.getHotLinks("motouk") } returns HttpError(429)
        val result = subject.execute("motouk")
        assertEquals(GetHotLinks.Result.Error, result)
    }

    @Test
    fun `Given a success response, When executed, Then return result`() = runBlocking {
        val mockResponse = mockk<Result.Success<List<Link>>> {
            every { response } returns mockk()
        }

        coEvery { listingApiClient.getHotLinks("motouk") } returns mockResponse

        val result = subject.execute("motouk")
        assertTrue(result is GetHotLinks.Result.Success)
    }
}
