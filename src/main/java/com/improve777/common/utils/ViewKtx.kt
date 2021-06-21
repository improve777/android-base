package com.improve777.common.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Rect
import android.view.TouchDelegate
import android.view.View
import android.view.ViewAnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BindingAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@BindingAdapter(value = ["visibleIf", "fade"], requireAll = false)
fun View.visibleIf(visible: Boolean?, fade: Boolean? = false) {
    if (fade == true) {
        if (visible == true) fadeIn() else fadeOut()
    }

    visibility = if (visible == true) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}
@BindingAdapter(value = ["goneIf"])
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

fun View.click(onClick: suspend (view: View) -> Unit): Flow<View> {
    return clicks()
        .throttleFirst(500)
        .onEach(onClick)
}

@BindingAdapter("onThrottleClick")
fun View.throttleClick(onClick: View.OnClickListener) {
    setOnClickListener(ThrottleClickListener(onClick, 500))
}

fun View.accessibleTouchTarget() {
    post {
        val delegateArea = Rect()
        getHitRect(delegateArea)

        // 48 dp is the minimum requirement. We need to convert this to pixels.
        val accessibilityMin = 48.PX

        // Calculate size vertically, and adjust touch area if it's smaller then the minimum.
        val height = delegateArea.bottom - delegateArea.top
        if (accessibilityMin > height) {
            // Add +1 px just in case min - height is odd and will be rounded down
            val addition = ((accessibilityMin - height) / 2).toInt() + 1
            delegateArea.top -= addition
            delegateArea.bottom += addition
        }

        // Calculate size horizontally, and adjust touch area if it's smaller than the minimum.
        val width = delegateArea.right - delegateArea.left
        if (accessibilityMin > width) {
            // add +1 px just in case min - width is odd and will be rounded down
            val addition = ((accessibilityMin - width) / 2).toInt() + 1
            delegateArea.left -= addition
            delegateArea.right += addition
        }

        val parentView = parent as? View
        parentView?.touchDelegate = TouchDelegate(delegateArea, this)
    }
}

fun View.fadeIn(duration: Long = 500) {
    animate()
        .alpha(1f)
        .setDuration(duration)
        .start()
}

fun View.fadeOut(duration: Long = 500) {
    animate()
        .alpha(0f)
        .setDuration(duration)
        .start()
}