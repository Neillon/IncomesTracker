package com.neillon.incomes_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.neillon.incomes_tracker.databinding.ActivityHomeBinding

const val LOGIN_ACTIVITY_CLASSNAME = "com.neillon.login.LoginActivity"

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val manager: SplitInstallManager by lazy { SplitInstallManagerFactory.create(this) }
    private val loginModuleName by lazy { getString(R.string.module_login) }

    private val listener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                showInformationDialog("Requires user confirmation")
            }
            SplitInstallSessionStatus.DOWNLOADING -> {
                showInformationDialog("Downloading")
            }
            SplitInstallSessionStatus.DOWNLOADED -> {
                showInformationDialog("Downloaded")
            }
            SplitInstallSessionStatus.INSTALLING -> {
                showInformationDialog("Installing")
            }
            SplitInstallSessionStatus.INSTALLED -> {
                showInformationDialog("Installed")
                launchActivity(LOGIN_ACTIVITY_CLASSNAME)
            }
            SplitInstallSessionStatus.FAILED -> {
                showInformationDialog("Failed to install.\nError: ${state.errorCode()}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    override fun onResume() {
        super.onResume()
        manager.registerListener(listener)
    }

    override fun onPause() {
        super.onPause()
        manager.registerListener(listener)
    }

    private fun initializeViews() {
        binding.mButtonSignIn.setOnClickListener {
            displayLogin()
        }
    }

    private fun displayLogin() {
        if (manager.installedModules.contains(loginModuleName)) {
            launchActivity(LOGIN_ACTIVITY_CLASSNAME)
        } else {
            val requestLogin = SplitInstallRequest.newBuilder()
                .addModule(loginModuleName)
                .build()

            manager.startInstall(requestLogin)
                .addOnCompleteListener { toastAndLog("Module $loginModuleName installed") }
                .addOnSuccessListener { toastAndLog("Loading $loginModuleName") }
                .addOnFailureListener { toastAndLog("Error Loading $loginModuleName") }
        }
    }

    private fun launchActivity(className: String) {
        Intent().setClassName(BuildConfig.APPLICATION_ID, className)
            .also {
                startActivity(it)
            }
    }

    private fun showInformationDialog(message: String) {
        MaterialAlertDialogBuilder(this@HomeActivity)
            .setTitle("Info")
            .setMessage(message)
            .show()
    }

    private fun toastAndLog(message: String) {
        Toast.makeText(this@HomeActivity, message, Toast.LENGTH_LONG).show()
        Log.d(TAG, message)
    }

    companion object {
        const val TAG = "HomeActivity"
    }
}
