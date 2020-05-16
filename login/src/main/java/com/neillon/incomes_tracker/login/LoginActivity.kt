package com.neillon.incomes_tracker.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.neillon.incomes_tracker.signup.SignUpActivity
import com.neillon.login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding.mTextViewSignUp.setOnClickListener { gotoSignUpActivity() }
    }

    private fun gotoSignUpActivity() {
        val signUpIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(signUpIntent)
    }
}
