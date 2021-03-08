package dev.mcd.redditsortof.data.reddit.api

import dev.mcd.redditsortof.data.reddit.api.ListingApiClient.Result
import dev.mcd.redditsortof.data.reddit.api.ListingApiClient.Result.HttpError
import dev.mcd.redditsortof.data.reddit.api.ListingApiClient.Result.Success
import dev.mcd.redditsortof.data.reddit.listing.Link
import dev.mcd.redditsortof.data.reddit.listing.ListingResponse
import dev.mcd.redditsortof.data.reddit.listing.toDomain
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import dev.mcd.redditsortof.domain.reddit.listing.Link as DomainLink

interface ListingApiClient {
    sealed class Result<T> {
        class Success<T>(val response: T) : Result<T>()
        class HttpError<T>(val code: Int) : Result<T>()
    }

    suspend fun getHotLinks(subreddit: String): Result<List<DomainLink>>
}

class ListingApiClientImpl @Inject constructor(
    private val retrofit: Retrofit,
) : ListingApiClient {

    private interface ListingApi {
        @GET("/{subreddit}/hot.json")
        fun hotLinks(@Path("subreddit") subreddit: String): Call<ListingResponse<Link>>
    }

    private val listingApi by lazy {
        retrofit.create(ListingApi::class.java)
    }

    override suspend fun getHotLinks(subreddit: String): Result<List<DomainLink>> {
        return executeRequest(listingApi.hotLinks(subreddit)) {
            it.data.children.map { listing -> listing.data.toDomain }
        }
    }

    private fun <Body, Out> executeRequest(call: Call<Body>, map: (Body) -> Out): Result<Out> {
        val response = call.execute()
        return if (response.isSuccessful) {
            Success(map(response.body()!!))
        } else {
            HttpError(response.code())
        }
    }
}