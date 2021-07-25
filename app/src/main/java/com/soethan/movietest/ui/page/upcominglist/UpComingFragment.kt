package com.soethan.movietest.ui.page.upcominglist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.soethan.movietest.R
import com.soethan.movietest.databinding.FragmentUpComingBinding
import com.soethan.movietest.ui.base.BaseFragment
import com.soethan.movietest.ui.page.popularlist.PopularViewModel
import com.soethan.movietest.utils.setToolbarAction
import com.soethan.movietest.utils.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UpComingFragment :
    BaseFragment<FragmentUpComingBinding>() {
    val viewModel: UpcomingViewModel by viewModel()

    private val upComingMoviesAdapter: UpComingMoviesAdapter by lazy {
        UpComingMoviesAdapter {
            val action = UpComingFragmentDirections.toMovieDetail(it.id)
            findNavController().navigate(action)
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentUpComingBinding {
        return FragmentUpComingBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpRecyclerView()
        observeData()

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            viewModel.getUpComingMovies()
            viewLifecycleOwner.lifecycleScope.launch {
                delay(5_000)
                binding.refresh.isRefreshing = false
            }
        }

    }

    private fun setUpRecyclerView() {
        binding.rvUpComing.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvUpComing.adapter = upComingMoviesAdapter
        binding.rvUpComing.setHasFixedSize(true)
    }

    private fun observeData() {
        viewModel.upComingUiMovieListState.observe(viewLifecycleOwner) { movieState ->
            binding.progress.isVisible = movieState.isLoading
            upComingMoviesAdapter.submitList(movieState.movies)
            movieState.throwable?.let {
                requireContext().toast(it.message)
            }
        }
    }

    private fun setUpToolbar() {
        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_favorite -> findNavController().navigate(R.id.favoriteFragment)
        }
        return true
    }

}