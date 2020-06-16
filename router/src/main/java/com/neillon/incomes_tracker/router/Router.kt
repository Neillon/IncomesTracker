package com.neillon.incomes_tracker.router

import android.app.Activity
import android.content.Intent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.koinApplication

object Router {

    fun navigateToFeature(activity: Activity, feature: Feature, flags: Int = 0) {
        koinApplication { unloadKoinModules(feature.modules) }
        koinApplication { loadKoinModules(feature.modules) }

        val intent = feature.makeNewIntent(flags)

        activity.startActivity(intent)
    }

    private fun Feature.makeNewIntent(flags: Int): Intent =
        Intent()
            .setClassName(entryPackageName, this.entryActivityName)
            .apply { addFlags(flags) }
}