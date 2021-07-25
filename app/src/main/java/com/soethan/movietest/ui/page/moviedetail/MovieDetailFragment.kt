package com.soethan.movietest.ui.page.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import com.soethan.movietest.R
import com.soethan.movietest.databinding.FragmentMovieDetailBinding
import com.soethan.movietest.models.MovieDetailUiModel
import com.soethan.movietest.ui.base.BaseFragment
import com.soethan.movietest.ui.page.upcominglist.UpcomingViewModel
import com.soethan.movietest.utils.loadImg
import com.soethan.movietest.utils.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    val viewModel: MovieDetailViewModel by viewModel()

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = MovieDetailFragmentArgs.fromBundle(requireArguments()).id
        observeData()
        viewModel.getDetail(id)
        viewModel.checkFavoriteStatus(id)
        binding.addFavourite.setOnClickListener {
            viewModel.editFavoriteStatus()
        }

    }

    private fun observeData() {
        viewModel.movieDetailState.observe(viewLifecycleOwner, Observer { state ->
            state.throwable?.let {
                requireContext().toast(it.message)
            }
            state.movie?.let {
                bindUi(state.movie)
            }

        })
        viewModel.isFavoriteMovieLiveData.observe(viewLifecycleOwner, Observer {
            binding.addFavourite.isChecked = it
        })
    }

    private fun bindUi(movieDetailUi: MovieDetailUiModel) {

        requireContext().loadImg(
            movieDetailUi.backdrop_path, binding.imgBackDrop, R.drawable.ic_photo,
            R.drawable.ic_photo
        )

        requireContext().loadImg(
            movieDetailUi.poster_path, binding.detailContent.posterImage, R.drawable.ic_photo,
            R.drawable.ic_photo
        )
        binding.detailContent.movieOverview.text = movieDetailUi.overview
        binding.detailContent.tvTitle.text = movieDetailUi.title
        binding.detailContent.movieLang.text = movieDetailUi.lang
        binding.detailContent.movieRat.text = movieDetailUi.vote_average.toString()
        binding.detailContent.movieRelease.text = movieDetailUi.release_date
    }

}