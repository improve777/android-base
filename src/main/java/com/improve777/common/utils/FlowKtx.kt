package com.improve777.common.utils

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalCoroutinesApi::class)
inline fun View.clicks() = callbackFlow {
    val listener = View.OnClickListener {
        kotlin.runCatching { offer(it) }.recoverCatching { false }
    }

    this@clicks.setOnClickListener(listener)
    awaitClose { this@clicks.setOnClickListener(null) }
}


fun <T> Flow<T>.throttleFirst(periodMillis: Long): Flow<T> {
    require(periodMillis > 0) { "period should be positive" }

    return flow {
        var lastTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            val l = currentTime - lastTime
            if (l >= periodMillis) {
                lastTime = currentTime
                emit(value)
            }
        }
    }
}