package dev.mcd.redditsortof.data.reddit.listing

import dev.mcd.redditsortof.data.reddit.common.FullnameKind

/**
 * https://www.reddit.com/dev/api/#listings
 */
data class ListingResponse<T : ListingType>(val data: ListingResponseData<T>)

data class ListingResponseData<T : ListingType>(val children: List<Listing<T>>)

data class Listing<T : ListingType>(
    val kind: FullnameKind,
    val data: T,
)
