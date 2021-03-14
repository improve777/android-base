package com.improve777.common.recyclerview

import androidx.recyclerview.widget.DiffUtil

class NoDiffCallback<T : DiffModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return false
    }
}