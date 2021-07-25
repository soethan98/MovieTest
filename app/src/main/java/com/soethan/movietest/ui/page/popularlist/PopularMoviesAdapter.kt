package com.soethan.movietest.ui.page.popularlist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.soethan.movietest.databinding.ItemMovieContentBinding
import com.soethan.movietest.models.PopularUiModel
import com.soethan.movietest.models.UpComingUiModel
import com.soethan.movietest.ui.base.adapter.BaseAdapter
import com.soethan.movietest.ui.base.adapter.ClickListener
import com.soethan.movietest.ui.page.upcominglist.UpComingViewHolder

class PopularMoviesAdapter(private val clickListener: ClickListener<PopularUiModel>) :
    BaseAdapter<PopularUiModel, PopularMovieViewHolder>() {

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding =
            ItemMovieContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularMovieViewHolder(binding, clickListener = clickListener)
    }
}