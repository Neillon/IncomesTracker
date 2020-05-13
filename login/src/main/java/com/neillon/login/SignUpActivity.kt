package com.neillon.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.neillon.login.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mButtonSignUp.setOnClickListener { goToHomeFeature() }
    }

    private fun goToHomeFeature() {
        Toast.makeText(this@SignUpActivity, "Go to Home feature", Toast.LENGTH_LONG).show()
    }
}
