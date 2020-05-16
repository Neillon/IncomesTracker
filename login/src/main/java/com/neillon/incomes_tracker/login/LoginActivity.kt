package com.neillon.incomes_tracker.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import com.neillon.common.ui.DynamicFeatureActivity
import com.neillon.incomes_tracker.router.DASHBOARD_MODULE_NAME
import com.neillon.incomes_tracker.router.Feature
import com.neillon.incomes_tracker.router.ModuleRequester
import com.neillon.incomes_tracker.router.Router
import com.neillon.incomes_tracker.signup.SignUpActivity
import com.neillon.login.databinding.ActivityLoginBinding

class LoginActivity : DynamicFeatureActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        binding.mButtonLogin.setOnClickListener { gotoDashboardActivity() }
        binding.mTextViewSignUp.setOnClickListener { gotoSignUpActivity() }
    }

    private fun gotoSignUpActivity() {
        val signUpIntent = Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(signUpIntent)
    }

    private fun gotoDashboardActivity() {
        ModuleRequester
            .addContext(this@LoginActivity)
            .addListener(this@LoginActivity)
            .requestAndInstallModule(DASHBOARD_MODULE_NAME)
            .addOnCompleteListener {
                Router.navigateToFeature(this@LoginActivity, Feature.Dashboard)
            }
            .addOnSuccessListener { toastAndLog("Loading $DASHBOARD_MODULE_NAME") }
            .addOnFailureListener { toastAndLog("Failed to install module") }
    }

    private fun toastAndLog(message: String) {
        Toast.makeText(this@LoginActivity, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)
    }

    companion object {
        const val TAG = "HomeActivity"
    }
}
