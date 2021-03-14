package com.improve777.common.utils

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

class DoubleTabFinishHandler(
    private val lifecycleScope: LifecycleCoroutineScope,
    private val duration: Int = 2000,
    onTab: () -> Unit,
) {
    private val initialPastTime = System.currentTimeMillis() - 100_000

    private val stateFlow = MutableStateFlow(initialPastTime)

    init {
        lifecycleScope.launch {
            stateFlow.drop(1)
                .collect {
                    onTab()
                }
        }
    }

    fun onBackPressed(onFinish: () -> Unit) {
        val current = System.currentTimeMillis()
        val limit = stateFlow.value + duration
        if (current > limit) {
            lifecycleScope.launch {
                stateFlow.emit(current)
            }
        } else {
            onFinish()
        }
    }
}