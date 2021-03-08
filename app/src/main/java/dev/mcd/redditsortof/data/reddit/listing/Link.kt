package dev.mcd.redditsortof.data.reddit.listing

import com.squareup.moshi.Json

/**
 * Represents a Link object
 * https://www.reddit.com/dev/api/#fullnames
 */
data class Link(
    @Json(name = "author_fullname")
    val authorFullname: String,
    @Json(name = "created_utc")
    val createdUtc: String,
    val downs: String,
    val isSelf: String,
    @Json(name = "num_comments")
    val numComments: String,
    val score: String,
    @Json(name = "selftext")
    val selfText: String,
    val title: String,
    val ups: String,
) : ListingType
