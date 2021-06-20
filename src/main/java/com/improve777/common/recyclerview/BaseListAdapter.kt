package com.improve777.common.recyclerview

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

@Suppress("UNCHECKED_CAST")
abstract class BaseListAdapter<T : DiffModel, B : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T> = BaseDiffCallback as DiffUtil.ItemCallback<T>,
) : ListAdapter<T, BindViewHolder<T, B>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder<T, B> {
        return BindViewHolder(viewType, parent)
    }

    override fun onBindViewHolder(holder: BindViewHolder<T, B>, position: Int) {
        holder.bind(getItem(position))
    }
}