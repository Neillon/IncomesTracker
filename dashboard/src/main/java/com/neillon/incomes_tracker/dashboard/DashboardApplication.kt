package com.neillon.incomes_tracker.dashboard

import android.app.Application
import timber.log.Timber

class DashboardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (androidx.viewbinding.BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}