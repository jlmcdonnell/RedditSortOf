package dev.mcd.redditsortof.data.listing

import dev.mcd.redditsortof.domain.listing.GetHotLinks
import dev.mcd.redditsortof.domain.listing.GetHotLinks.Result.Error
import dev.mcd.redditsortof.domain.listing.GetHotLinks.Result.Success
import timber.log.Timber
import javax.inject.Inject

class GetHotLinksImpl @Inject constructor(
    private val listingApiClient: ListingApiClient,
) : GetHotLinks {
    override suspend fun execute(subreddit: String): GetHotLinks.Result {
        return when (val result = listingApiClient.getHotLinks(subreddit)) {
            is ListingApiClient.Result.HttpError -> {
                // No need to handle this yet, log for now
                Timber.d("HTTP Error: ${result.code}")
                Error
            }
            is ListingApiClient.Result.Success -> {
                Success(result.response)
            }
        }
    }
}
