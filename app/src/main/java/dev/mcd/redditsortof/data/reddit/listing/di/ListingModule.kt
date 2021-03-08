package dev.mcd.redditsortof.data.reddit.listing.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mcd.redditsortof.data.reddit.api.ListingApiClient
import dev.mcd.redditsortof.data.reddit.api.ListingApiClientImpl
import dev.mcd.redditsortof.data.reddit.common.di.RedditApi
import dev.mcd.redditsortof.data.reddit.listing.GetHotLinksImpl
import dev.mcd.redditsortof.domain.reddit.listing.GetHotLinks
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class ListingModule {

    @Provides
    fun listingApi(@RedditApi retrofit: Retrofit): ListingApiClient = ListingApiClientImpl(retrofit)

    @Provides
    fun getHotLinks(apiClient: ListingApiClient): GetHotLinks = GetHotLinksImpl(apiClient)
}
