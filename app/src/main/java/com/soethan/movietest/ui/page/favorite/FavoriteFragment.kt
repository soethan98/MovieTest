package com.soethan.movietest.ui.page.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.soethan.movietest.R
import com.soethan.movietest.databinding.FragmentFavoriteBinding
import com.soethan.movietest.databinding.FragmentPopularBinding
import com.soethan.movietest.ui.base.BaseFragment
import com.soethan.movietest.ui.page.popularlist.PopularFragmentDirections
import com.soethan.movietest.ui.page.popularlist.PopularMoviesAdapter
import com.soethan.movietest.ui.page.popularlist.PopularViewModel
import com.soethan.movietest.utils.setToolbarAction
import com.soethan.movietest.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    val viewModel: FavoriteViewModel by viewModel()

    private val favoriteMoviesAdapter: FavoriteMoviesAdapter by lazy {
        FavoriteMoviesAdapter {
            val action = FavoriteFragmentDirections.toMovieDetail(it.id)
            findNavController().navigate(action)
        }
    }
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(layoutInflater, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpRecyclerView()
        observeData()

    }

    private fun setUpRecyclerView() {
        binding.rvFavorite.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = favoriteMoviesAdapter
            setHasFixedSize(true)
        }

    }

    private fun observeData() {
        viewModel.favoriteUiMovieListState.observe(viewLifecycleOwner) { movieState ->
            binding.progress.isVisible = movieState.isLoading
            favoriteMoviesAdapter.submitList(movieState.movies)
            movieState.throwable?.let {
                requireContext().toast(it.message)
            }

        }
    }

    private fun setUpToolbar(){
        setHasOptionsMenu(true)
        binding.favToolbar.setToolbarAction(requireActivity() as AppCompatActivity)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
             android.R.id.home -> findNavController().navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }

}