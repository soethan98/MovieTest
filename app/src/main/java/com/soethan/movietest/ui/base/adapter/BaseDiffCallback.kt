package com.soethan.movietest.ui.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.soethan.movietest.models.BaseUiModel

class BaseDiffCallback<T : BaseUiModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }


}