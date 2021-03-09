package dev.mcd.redditsortof.data.environment

import dev.mcd.redditsortof.BuildConfig
import javax.inject.Inject

interface Environment {
    val redditApiHost: String
    val demoSubredditName: String
}

class EnvironmentImpl @Inject constructor() : Environment {
    override val redditApiHost: String
        get() = BuildConfig.REDDIT_API_HOST

    override val demoSubredditName: String
        get() = BuildConfig.SHOWCASE_SUBREDDIT_SLUG
}
