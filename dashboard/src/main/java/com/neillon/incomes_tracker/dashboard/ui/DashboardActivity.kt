package com.neillon.incomes_tracker.dashboard.ui

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.dashboard.databinding.ActivityDashboardBinding


class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        binding.mImageButtonShowBalance.setOnClickListener {
            if (binding.mTextViewBalance.alpha > 0.5){
                startAlphaAnimationOnView(binding.mTextViewBalance, 1.0f, 0.0f)
                startChangeIconAnimationOnView(binding.mImageButtonShowBalance, R.drawable.ic_baseline_remove_red_eye, R.drawable.ic_baseline_visibility_off)
            } else {
                startAlphaAnimationOnView(binding.mTextViewBalance, 0.0f, 1.0f)
                startChangeIconAnimationOnView(binding.mImageButtonShowBalance, R.drawable.ic_baseline_visibility_off, R.drawable.ic_baseline_remove_red_eye)
            }

        }
    }

    private fun startChangeIconAnimationOnView(view: ImageButton, initial: Int, final: Int) {
        val changeImageResourceAnimator = ObjectAnimator.ofInt(view, "imageResource", initial, final)
        changeImageResourceAnimator.target = view
        changeImageResourceAnimator.duration = 700L
        changeImageResourceAnimator.start()
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