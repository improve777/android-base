package com.improve777.common.livedata

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

interface ViewLifecycleHolder {

    val viewLifecycleHolder: LifecycleOwner

    fun <T> LiveData<T>.observe(onHandle: (T) -> Unit) {
        observe(viewLifecycleHolder, onHandle)
    }

    fun <T> LiveData<Event<T>>.eventObserve(onEventUnhandledContent: (T) -> Unit) {
        observe(viewLifecycleHolder, EventObserver(onEventUnhandledContent))
    }
}