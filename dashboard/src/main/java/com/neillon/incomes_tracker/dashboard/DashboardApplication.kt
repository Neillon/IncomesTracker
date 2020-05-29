package com.neillon.incomes_tracker.dashboard

import android.app.Application
import com.neillon.incomes_tracker.dashboard.di.DashboardAppComponent
import timber.log.Timber

class DashboardApplication : Application() {

    private lateinit var dashboardAppComponent: DashboardAppComponent

    override fun onCreate() {
        super.onCreate()

        if (androidx.viewbinding.BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}