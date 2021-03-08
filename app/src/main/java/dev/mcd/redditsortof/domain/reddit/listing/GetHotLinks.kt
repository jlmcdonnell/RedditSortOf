package dev.mcd.redditsortof.domain.reddit.listing

interface GetHotLinks {
    sealed class Result {
        class Success(val links: List<Link>) : Result()
        object Error : Result()
    }

    suspend fun execute(subreddit: String): Result
}
