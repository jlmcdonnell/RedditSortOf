package dev.mcd.redditsortof.domain.listing

interface GetDemoSubredditName {
    suspend fun execute(): String
}
