package dev.mcd.redditsortof.data.reddit.listing

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Represents a Link object
 * https://www.reddit.com/dev/api/#fullnames
 */
@JsonClass(generateAdapter = true)
data class Link(
    val author: String,
    @get:Json(name = "created_utc")
    val createdUtc: Long,
    val downs: Int,
    @get:Json(name = "is_self")
    val isSelf: Boolean,
    @get:Json(name = "num_comments")
    val numComments: Int,
    val score: Int,
    @get:Json(name = "selftext")
    val selfText: String,
    val title: String,
    val ups: Int,
) : ListingType
