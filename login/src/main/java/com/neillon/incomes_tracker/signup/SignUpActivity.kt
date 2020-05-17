package com.neillon.incomes_tracker.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.neillon.login.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding.mButtonSignUp.setOnClickListener { goToHomeFeature() }
    }

    private fun goToHomeFeature() {
        Toast.makeText(this@SignUpActivity, "Go to Home feature", Toast.LENGTH_LONG).show()
    }
}