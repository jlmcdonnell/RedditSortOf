package dev.mcd.redditsortof.presentation.listing

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import dev.mcd.redditsortof.R
import dev.mcd.redditsortof.databinding.ListingFragmentBinding
import dev.mcd.redditsortof.domain.listing.Link
import dev.mcd.redditsortof.presentation.listing.ListingViewModel.State
import dev.mcd.redditsortof.presentation.listing.ListingViewModel.State.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ListingFragment : Fragment(R.layout.listing_fragment) {

    private val binding by lazy { ListingFragmentBinding.bind(requireView()) }
    private val viewModel by viewModels<ListingViewModel>()
    private val adapter by lazy { GroupieAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupVM()
    }

    private fun setupUI() {
        binding.linksView.adapter = adapter
        binding.linksView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setupVM() {
        viewModel.state
            .onEach(::handleState)
            .launchIn(lifecycleScope)
    }

    private fun handleState(state: State) {
        when (state) {
            is NavigateToLink -> TODO()
            is ShowError -> showError()
            is DisplayLinks -> displayLinks(state.links)
            is HideLoading -> hideLoading()
            is ShowLoading -> showLoading()
        }
    }

    private fun showLoading() {
        binding.linksView.isVisible = false
        binding.progress.isVisible = true
    }

    private fun hideLoading() {
        binding.linksView.isVisible = true
        binding.progress.isVisible = false
    }

    private fun displayLinks(links: List<Link>) {
        adapter.clear()
        adapter.addAll(links.map(::LinkItem))
    }

    private fun showError() {
        Toast.makeText(requireContext(), R.string.generic_error, Toast.LENGTH_SHORT).show()
    }
}
