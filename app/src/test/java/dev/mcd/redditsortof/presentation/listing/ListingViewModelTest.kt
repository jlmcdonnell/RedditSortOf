package dev.mcd.redditsortof.presentation.listing

import dev.mcd.redditsortof.domain.listing.GetDemoSubredditName
import dev.mcd.redditsortof.domain.listing.GetHotLinks
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class ListingViewModelTest {

    @MockK
    lateinit var getDemoSubredditName: GetDemoSubredditName

    @MockK
    lateinit var getHotLinks: GetHotLinks

    @InjectMockKs
    lateinit var viewModel: ListingViewModel

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun `When started, Then show loading`() {
        TODO("Implement test")
    }
}
