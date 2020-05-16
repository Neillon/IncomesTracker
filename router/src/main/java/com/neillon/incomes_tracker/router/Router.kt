package com.neillon.incomes_tracker.router

import android.app.Activity
import android.content.Intent

object Router {

    fun navigateToFeature(activity: Activity, feature: Feature, flags: Int = 0) {
        activity.startActivity(feature.makeNewIntent(flags))
    }

    private fun Feature.makeNewIntent(flags: Int): Intent =
        Intent()
            .setClassName(entryPackageName, this.entryActivityName)
            .apply { addFlags(flags) }
}