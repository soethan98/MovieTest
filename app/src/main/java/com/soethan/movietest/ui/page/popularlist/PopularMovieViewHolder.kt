package com.soethan.movietest.ui.page.popularlist

import com.soethan.movietest.R
import com.soethan.movietest.databinding.ItemMovieContentBinding
import com.soethan.movietest.models.PopularUiModel
import com.soethan.movietest.models.UpComingUiModel
import com.soethan.movietest.ui.base.adapter.BaseViewHolder
import com.soethan.movietest.ui.base.adapter.ClickListener
import com.soethan.movietest.utils.loadImg

class PopularMovieViewHolder(
    private var binding: ItemMovieContentBinding,
    private var clickListener: ClickListener<PopularUiModel>
) :
    BaseViewHolder<PopularUiModel>(binding.root) {
    override fun bind(data: PopularUiModel) {
        binding.movieTitle.text = data.title
        binding.ratingCircle.text = data.vote_average.toString()
        binding.movieImage.context.loadImg(
            data.poster_path, binding.movieImage, R.drawable.ic_photo,
            R.drawable.ic_photo
        )

        binding.root.setOnClickListener { clickListener.invoke(data) }

    }
}