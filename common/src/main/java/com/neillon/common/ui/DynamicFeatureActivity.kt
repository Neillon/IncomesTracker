package com.neillon.common.ui

import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.neillon.incomes_tracker.router.Feature
import com.neillon.incomes_tracker.router.ModuleRequester
import com.neillon.incomes_tracker.router.Router
import com.neillon.incomes_tracker.router.RouterStatusListener

open class DynamicFeatureActivity : AppCompatActivity(), RouterStatusListener {

    override fun onResume() {
        super.onResume()
        ModuleRequester.addListener(this@DynamicFeatureActivity)
    }

    override fun onPause() {
        super.onPause()
        ModuleRequester.addListener(this@DynamicFeatureActivity)
    }

    override fun listenManagerStatus(): SplitInstallStateUpdatedListener =
        SplitInstallStateUpdatedListener { state ->
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
                    Router.navigateToFeature(this@DynamicFeatureActivity, Feature.Login)
                }
                SplitInstallSessionStatus.FAILED -> {
                    showInformationDialog("Failed to install.\nError: ${state.errorCode()}")
                }
            }
        }

    private fun showInformationDialog(message: String) {
        MaterialAlertDialogBuilder(this@DynamicFeatureActivity)
            .setTitle("Info")
            .setMessage(message)
            .show()
    }
}