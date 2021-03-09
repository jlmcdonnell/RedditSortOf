package dev.mcd.redditsortof.data.listing.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mcd.redditsortof.data.environment.Environment
import dev.mcd.redditsortof.data.listing.GetDemoSubredditNameImpl
import dev.mcd.redditsortof.data.listing.GetHotLinksImpl
import dev.mcd.redditsortof.data.listing.ListingApiClient
import dev.mcd.redditsortof.data.listing.ListingApiClientImpl
import dev.mcd.redditsortof.data.reddit.di.RedditApi
import dev.mcd.redditsortof.domain.listing.GetDemoSubredditName
import dev.mcd.redditsortof.domain.listing.GetHotLinks
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ListingModule {

    @Provides
    fun listingApi(@RedditApi retrofit: Retrofit): ListingApiClient {
        return ListingApiClientImpl(retrofit)
    }

    @Provides
    fun getHotLinks(apiClient: ListingApiClient): GetHotLinks {
        return GetHotLinksImpl(apiClient)
    }

    @Provides
    fun getDemoSubredditName(environment: Environment): GetDemoSubredditName {
        return GetDemoSubredditNameImpl(environment)
    }
}
