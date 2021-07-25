package com.soethan.movietest.ui.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.soethan.movietest.models.BaseUiModel

abstract class BaseViewHolder<T : BaseUiModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}