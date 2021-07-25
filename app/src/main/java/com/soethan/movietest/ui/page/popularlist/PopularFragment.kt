package com.soethan.movietest.ui.page.popularlist

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
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.soethan.movietest.R
import com.soethan.movietest.databinding.FragmentPopularBinding
import com.soethan.movietest.ui.base.BaseFragment
import com.soethan.movietest.utils.setToolbarAction
import com.soethan.movietest.utils.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class PopularFragment  : BaseFragment<FragmentPopularBinding>() {

    val viewModel:PopularViewModel by viewModel()

    private val popularMovieAdapter: PopularMoviesAdapter by lazy {
        PopularMoviesAdapter {
            val action = PopularFragmentDirections.toMovieDetail(it.id)
            findNavController().navigate(action)
        }
    }
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPopularBinding {
        return FragmentPopularBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        observeData()
        setUpToolbar()

        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            viewModel.getPopularMovies()
            lifecycleScope.launch {
                delay(5_000)
                binding.refresh.isRefreshing = false
            }
        }


    }

    private fun setUpRecyclerView() {
        binding.rvPopular.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = popularMovieAdapter
            setHasFixedSize(true)
        }

    }

    private fun observeData() {
        viewModel.popularUiMovieListState.observe(viewLifecycleOwner) { movieState ->
            binding.progress.isVisible = movieState.isLoading
            popularMovieAdapter.submitList(movieState.movies)
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