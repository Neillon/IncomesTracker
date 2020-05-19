package com.neillon.incomes_tracker.dashboard.ui

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.dashboard.databinding.ActivityDashboardBinding

private const val CURRENCY_PATTERN = "$ ---,--"

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private var changeBalance = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        binding.mImageButtonShowBalance.setOnClickListener {
            if (changeBalance){
                startAlphaAnimationOnView(binding.mTextViewBalance, 1.0f, 0.0f)
                binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_visibility_off)
                startAlphaAnimationOnView(binding.mTextViewBalance, 0.0f, 1.0f)
                binding.mTextViewBalance.text = CURRENCY_PATTERN
            } else {
                startAlphaAnimationOnView(binding.mTextViewBalance, 0.0f, 1.0f)
                binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_remove_red_eye)
                binding.mTextViewBalance.text = "$ 25,480.90"
            }
            changeBalance = !changeBalance
        }
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
}