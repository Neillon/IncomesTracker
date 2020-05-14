package com.neillon.incomes_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neillon.incomes_tracker.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() { }
}
