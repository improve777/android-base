package com.improve777.common.utils

import androidx.recyclerview.widget.RecyclerView
import com.improve777.common.recyclerview.EndlessRecyclerViewScrollListener

inline fun RecyclerView.onLoadMore(
    chunk: Int = 20,
    crossinline onScroll: (page: Int, totalItemsCount: Int) -> Unit,
) {
    clearOnScrollListeners()
    addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager!!) {
        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            if (totalItemsCount >= chunk) {
                onScroll(page, totalItemsCount)
            }
        }
    })
}
