package com.neillon.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Toast.makeText(this@LoginActivity, "Login", Toast.LENGTH_LONG).show()
    }
}
