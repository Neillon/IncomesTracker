package com.neillon.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.neillon.login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLayout()
    }

    private fun setupLayout() {
        binding.mTextViewSignUp.setOnClickListener { gotoSignUpActivity() }
    }

    private fun gotoSignUpActivity() {
        val signUpIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(signUpIntent)
    }
}
