package dev.mcd.redditsortof.data.reddit.api

import dev.mcd.redditsortof.data.reddit.api.ListingApiClient.Result.HttpError
import dev.mcd.redditsortof.data.reddit.api.ListingApiClient.Result.Success
import dev.mcd.redditsortof.data.reddit.listing.Link
import dev.mcd.redditsortof.data.reddit.listing.ListingResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject

interface ListingApiClient {
    sealed class Result<T> {
        class Success<T>(val response: T) : Result<T>()
        class HttpError<T>(val code: Int) : Result<T>()
    }

    fun hostLinks(subreddit: String): Result<ListingResponse<Link>>
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

    override fun hostLinks(subreddit: String): ListingApiClient.Result<ListingResponse<Link>> {
        return executeRequest(listingApi.hotLinks(subreddit))
    }

    private fun <T> executeRequest(call: Call<T>): ListingApiClient.Result<T> {
        val response = call.execute()
        return if (response.isSuccessful) {
            Success(response.body()!!)
        } else {
            HttpError(response.code())
        }
    }
}
