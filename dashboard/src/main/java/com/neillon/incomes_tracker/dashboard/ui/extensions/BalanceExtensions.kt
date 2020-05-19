package com.neillon.incomes_tracker.dashboard.ui.extensions

import android.animation.ObjectAnimator
import android.view.View
import android.widget.TextView

private const val CURRENCY_PATTERN = "$ ---,--"

fun TextView.showBalance(value: String) {
    startAlphaAnimationOnView(this, 0.0f, 1.0f)
    this.text = value
}

fun TextView.hideBalance() {
    startAlphaAnimationOnView(this, 1.0f, 0.0f)
    this.text = CURRENCY_PATTERN
    startAlphaAnimationOnView(this, 0.0f, 1.0f)
}

private fun startAlphaAnimationOnView(view: View, initial: Float, final: Float) {
    val fadeAnimator = ObjectAnimator.ofFloat(
        view,
        View.ALPHA,
        initial,
        final
    )

    fadeAnimator.duration = 700;
    fadeAnimator.start()
}