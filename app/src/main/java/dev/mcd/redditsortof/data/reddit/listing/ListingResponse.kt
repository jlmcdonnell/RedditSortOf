package dev.mcd.redditsortof.data.reddit.listing

import com.squareup.moshi.JsonClass
import dev.mcd.redditsortof.data.reddit.common.FullnameKind

/**
 * https://www.reddit.com/dev/api/#listings
 */
@JsonClass(generateAdapter = true)
data class ListingResponse<T : ListingType>(val data: ListingResponseData<T>)

@JsonClass(generateAdapter = true)
data class ListingResponseData<T : ListingType>(val children: List<Listing<T>>)

@JsonClass(generateAdapter = true)
data class Listing<T : ListingType>(
    val kind: FullnameKind,
    val data: T,
)
