package com.improve777.common.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.improve777.androidbase.BR
import timber.log.Timber

abstract class BindViewHolder<T, B : ViewDataBinding>(
    @LayoutRes layoutId: Int,
    parent: ViewGroup,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        layoutId,
        parent,
        false
    )
) {
    protected val binding: B = DataBindingUtil.bind<B>(itemView)!!

    open fun bind(item: T) {
        try {
            binding.setVariable(BR.item, item)
            binding.executePendingBindings()
        } catch(e: Exception) {
            Timber.w(e)
        }
    }
}