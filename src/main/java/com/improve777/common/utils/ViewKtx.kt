package com.improve777.common.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import android.view.ViewAnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["visibleIf"])
fun View.visibleIf(visible: Boolean?) {
    visibility = if (visible == true) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

@BindingAdapter(value = ["visibleIf"])
fun View.goneIf(visible: Boolean?) {
    visibility = if (visible == true) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

/**
 * circular reveal animation 뷰 표시 확장함수
 *
 * [https://github.com/codepath/android_guides/wiki/Circular-Reveal-Animation]
 */
fun View.enterReveal() {
    val cx = measuredWidth / 2
    val cy = measuredHeight / 2

    val finalRadius = (width.coerceAtLeast(height) / 2).toFloat()

    val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, 0f, finalRadius)

    visibility = View.VISIBLE
    anim.start()
}

/**
 * circular reveal animation 뷰 제거 확장함수
 *
 * [https://github.com/codepath/android_guides/wiki/Circular-Reveal-Animation]
 */
fun View.exitReveal() {
    val cx = measuredWidth / 2
    val cy = measuredHeight / 2

    val initialRadius = (width / 2).toFloat()

    val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, initialRadius, 0f)

    anim.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            visibility = View.INVISIBLE
        }
    })
    anim.start()
}

fun View.showKeyboard() {
    context.inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard(): Boolean {
    return try {
        context.inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (_: RuntimeException) {
        false
    }
}