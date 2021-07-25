package com.soethan.movietest.ui.page.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import com.soethan.movietest.databinding.ItemMovieContentBinding
import com.soethan.movietest.models.FavoriteUiModel
import com.soethan.movietest.models.PopularUiModel
import com.soethan.movietest.ui.base.adapter.BaseAdapter
import com.soethan.movietest.ui.base.adapter.ClickListener
import com.soethan.movietest.ui.page.popularlist.PopularMovieViewHolder

class FavoriteMoviesAdapter(private val clickListener: ClickListener<FavoriteUiModel>) :
    BaseAdapter<FavoriteUiModel, FavoriteMovieViewHolder>() {

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val binding =
            ItemMovieContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieViewHolder(binding, clickListener = clickListener)
    }
}