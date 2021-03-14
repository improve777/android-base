package com.improve777.common.recyclerview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

@Suppress("UNCHECKED_CAST")
abstract class BaseListAdapter<T : DiffModel, B : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T> = BaseDiffCallback as DiffUtil.ItemCallback<T>,
) : ListAdapter<T, BindViewHolder<T, B>>(diffCallback) {

    override fun onBindViewHolder(holder: BindViewHolder<T, B>, position: Int) {
        holder.bind(getItem(position))
    }
}