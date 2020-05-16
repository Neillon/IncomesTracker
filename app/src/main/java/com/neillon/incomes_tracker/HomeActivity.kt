package com.neillon.incomes_tracker

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.neillon.common.ui.DynamicFeatureActivity
import com.neillon.incomes_tracker.databinding.ActivityHomeBinding
import com.neillon.incomes_tracker.router.*

class HomeActivity : DynamicFeatureActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        binding.mButtonSignIn.setOnClickListener { displayLogin() }
    }

    private fun displayLogin() {
        ModuleRequester
            .addContext(this@HomeActivity)
            .addListener(this@HomeActivity)
            .requestAndInstallModule(LOGIN_MODUlE_NAME)
            .addOnCompleteListener {
                Router.navigateToFeature(this@HomeActivity, Feature.Login)
            }
            .addOnSuccessListener { toastAndLog("Loading $LOGIN_MODUlE_NAME") }
            .addOnFailureListener { toastAndLog("Failed to install module") }
    }

    private fun toastAndLog(message: String) {
        Toast.makeText(this@HomeActivity, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)
    }

    companion object {
        const val TAG = "HomeActivity"
    }
}
