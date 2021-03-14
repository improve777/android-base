package com.improve777.common.recyclerview

import androidx.recyclerview.widget.DiffUtil

object BaseDiffCallback : DiffUtil.ItemCallback<DiffModel>() {
    override fun areItemsTheSame(oldItem: DiffModel, newItem: DiffModel): Boolean {
        return oldItem.diffId == newItem.diffId
    }

    override fun areContentsTheSame(oldItem: DiffModel, newItem: DiffModel): Boolean {
        return oldItem == newItem
    }
}