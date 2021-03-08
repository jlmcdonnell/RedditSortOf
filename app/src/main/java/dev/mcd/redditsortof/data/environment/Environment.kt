package dev.mcd.redditsortof.data.environment

import dev.mcd.redditsortof.BuildConfig
import javax.inject.Inject

interface Environment {
    val redditApiHost: String
    val showcaseSubreddit: String
}

class EnvironmentImpl @Inject constructor() : Environment {
    override val redditApiHost: String
        get() = BuildConfig.REDDIT_API_HOST

    override val showcaseSubreddit: String
        get() = BuildConfig.SHOWCASE_SUBREDDIT_SLUG
}
