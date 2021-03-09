package dev.mcd.redditsortof.presentation.listing

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mcd.redditsortof.domain.listing.GetDemoSubredditName
import dev.mcd.redditsortof.domain.listing.GetHotLinks
import dev.mcd.redditsortof.domain.listing.GetHotLinks.Result
import dev.mcd.redditsortof.domain.listing.Link
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(
    private val getDemoSubredditName: GetDemoSubredditName,
    private val getHotLinks: GetHotLinks,
) : ViewModel() {

    sealed class State {
        class DisplayLinks(val links: List<Link>) : State()
        class OpenPost(val url: String) : State()
        object ShowLoading : State()
        object HideLoading : State()
        object ShowError : State()
    }

    private val eventState = MutableSharedFlow<State>()

    val state: Flow<State>
        get() = flow {
            emit(State.ShowLoading)

            val result = getDemoSubredditName.execute()
                .let { name -> getHotLinks.execute(name) }

            when (result) {
                // There's currently no difference in handling an exception and an error result.
                // An error result may be used for non-exceptional behaviour such as an HTTP 401 (Unauthenticated) that
                // should be handled as business logic, and not as an exception.
                is Result.Error -> emit(State.ShowError)
                is Result.Success -> emit(State.DisplayLinks(result.links))
            }

            emit(State.HideLoading)
            eventState.collect(::emit)
        }.flowOn(Dispatchers.IO)
            .catch {
                Timber.e(it, "loading data")
                emit(State.ShowError)
                emit(State.HideLoading)
            }

    fun onLinkClicked(link: Link) {
        eventState.tryEmit(State.OpenPost(link.url))
    }
}
