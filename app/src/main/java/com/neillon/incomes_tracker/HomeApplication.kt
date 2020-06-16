package com.neillon.incomes_tracker

import android.app.Application
import com.neillon.incomes_tracker.persistence.di.PersistenceModule.persistenceModule
import com.neillon.incomes_tracker.usecase.di.UseCaseModule.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class HomeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@HomeApplication)
            modules(
                persistenceModule,
                useCaseModule,
                dashboardModule
            )
        }
    }
}