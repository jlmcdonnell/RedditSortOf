package dev.mcd.redditsortof.data.reddit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mcd.redditsortof.data.environment.Environment
import dev.mcd.redditsortof.data.reddit.api.ListingApiClient
import dev.mcd.redditsortof.data.reddit.api.ListingApiClientImpl
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class RedditModule {

    @Provides
    @RedditApiHost
    fun redditApiHost(environment: Environment): String {
        return environment.redditApiHost
    }

    @Provides
    @RedditApi
    fun retrofit(@RedditApiHost apiHost: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiHost)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun listingApi(@RedditApi retrofit: Retrofit): ListingApiClient {
        return ListingApiClientImpl(retrofit)
    }
}
