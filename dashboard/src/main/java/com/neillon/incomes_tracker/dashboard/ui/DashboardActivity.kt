package com.neillon.incomes_tracker.dashboard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.dashboard.databinding.ActivityDashboardBinding
import com.neillon.incomes_tracker.dashboard.ui.extensions.hideBalance
import com.neillon.incomes_tracker.dashboard.ui.extensions.showBalance

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
            if (changeBalance) {
                binding.mTextViewBalance.hideBalance()
                binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_visibility_off)
            } else {
                binding.mTextViewBalance.showBalance(value = "$ 25,480.90")
                binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_remove_red_eye)
            }
            changeBalance = !changeBalance
        }
    }
}