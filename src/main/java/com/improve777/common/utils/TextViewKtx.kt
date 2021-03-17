package com.improve777.common.utils

import android.text.style.LeadingMarginSpan
import android.widget.TextView
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import androidx.databinding.BindingAdapter

@BindingAdapter("android:text", "app:textIndent")
fun TextView.setTextIndent(value: String, indent: Float) {
    text = value
    val span = LeadingMarginSpan.Standard(0, indent.toInt())
    text = buildSpannedString {
        inSpans(span) {
            append(text)
        }
    }
}