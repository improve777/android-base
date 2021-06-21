package com.improve777.common.utils

import android.view.View

class ThrottleClickListener(
    private val listener: View.OnClickListener,
    private val periodMillis: Long,
) : View.OnClickListener {

    private var lastTime: Long = 0L

    override fun onClick(v: View?) {
        val currentTime = System.currentTimeMillis()
        val l = currentTime - lastTime
        if (l >= periodMillis) {
            lastTime = currentTime
            listener.onClick(v)
        }
    }
}