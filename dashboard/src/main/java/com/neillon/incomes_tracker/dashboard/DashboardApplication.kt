package com.neillon.incomes_tracker.dashboard

import android.app.Application
import timber.log.Timber

class DashboardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}