package dev.mcd.redditsortof.data.listing

import dev.mcd.redditsortof.data.environment.Environment
import dev.mcd.redditsortof.domain.listing.GetDemoSubredditName
import javax.inject.Inject

class GetDemoSubredditNameImpl @Inject constructor(
    private val environment: Environment,
) : GetDemoSubredditName {

    override suspend fun execute(): String {
        return environment.demoSubredditName
    }
}
