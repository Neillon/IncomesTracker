package com.neillon.incomes_tracker.router.contracts

import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener

interface RouterStatusListener {
    fun listenManagerStatus(): SplitInstallStateUpdatedListener
}