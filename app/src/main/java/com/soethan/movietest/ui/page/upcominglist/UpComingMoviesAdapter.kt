package com.soethan.movietest.ui.page.upcominglist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.soethan.movietest.databinding.ItemMovieContentBinding
import com.soethan.movietest.models.UpComingUiModel
import com.soethan.movietest.ui.base.adapter.BaseAdapter
import com.soethan.movietest.ui.base.adapter.ClickListener

class UpComingMoviesAdapter(private val clickListener: ClickListener<UpComingUiModel>) :
    BaseAdapter<UpComingUiModel, UpComingViewHolder>() {

    override fun onBindViewHolder(holder: UpComingViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpComingViewHolder {
        val binding =
            ItemMovieContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UpComingViewHolder(binding, clickListener = clickListener)
    }
}