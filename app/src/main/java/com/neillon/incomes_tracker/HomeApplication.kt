package com.neillon.incomes_tracker

import android.app.Application
import timber.log.Timber

class HomeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}