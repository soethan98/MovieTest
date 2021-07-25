package com.soethan.movietest.ui.base.adapter

import androidx.recyclerview.widget.ListAdapter
import com.soethan.movietest.models.BaseUiModel

abstract class BaseAdapter<T : BaseUiModel, H : BaseViewHolder<T>> :
    ListAdapter<T, H>(BaseDiffCallback())

typealias  ClickListener<T> = (T) -> Unit