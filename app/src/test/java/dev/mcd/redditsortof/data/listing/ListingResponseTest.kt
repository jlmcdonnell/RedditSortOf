package dev.mcd.redditsortof.data.listing

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dev.mcd.redditsortof.data.reddit.fullnameTypeLink
import okio.Okio
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ListingResponseTest {

    private lateinit var moshi: Moshi
    private lateinit var listingAdapter: JsonAdapter<ListingResponse<Link>>

    @Before
    fun setUp() {
        moshi = Moshi.Builder()
            .build()
        val type = Types.newParameterizedType(ListingResponse::class.java, Link::class.java)
        listingAdapter = moshi.adapter(type)
    }

    @Test
    fun `Given a sample Listening, Then parse response successfully`() {
        val reader = readListingResponse()
        val response = listingAdapter.fromJson(reader)!!

        val firstLink = Link(
            author = "curated_android",
            createdUtc = 1615204819,
            downs = 0,
            isSelf = true,
            numComments = 21,
            score = 4,
            selfText = "test selftext",
            title = "test title",
            ups = 4,
            url = "https://www.reddit.com/r/Android/comments/m0e80k/moronic_monday_mar_08_2021_your_weekly_questions",
        )

        val listings = response.data.children
        assertTrue(listings.all { it.kind == fullnameTypeLink })
        assertEquals(listings.size, 27)
        assertEquals(listings.first().data, firstLink)
    }

    private fun readListingResponse(): JsonReader {
        return ClassLoader.getSystemClassLoader()
            .getResourceAsStream("sample-listing-response.json")
            .let(Okio::source)
            .let(Okio::buffer)
            .let(JsonReader::of)
    }
}
