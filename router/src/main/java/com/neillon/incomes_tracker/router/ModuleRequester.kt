package com.neillon.incomes_tracker.router

import android.content.Context
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.tasks.Task
import com.neillon.incomes_tracker.router.contracts.RouterStatusListener

object ModuleRequester {
    private lateinit var mContext: Context
    private lateinit var mStatusListener: SplitInstallStateUpdatedListener
    private val mManager: SplitInstallManager by lazy { SplitInstallManagerFactory.create(mContext) }

    fun addContext(context: Context): ModuleRequester {
        mContext = context
        return this
    }

    fun addListener(listener: RouterStatusListener): ModuleRequester {
        mStatusListener = listener.listenManagerStatus()
        return this
    }

    fun requestAndInstallModule(module: String): Task<Int> {
        val request = requestModule(module)
        return installModule(request)!!
    }

    private fun requestModule(module: String) = SplitInstallRequest.newBuilder()
        .addModule(module)
        .build()

    private fun installModule(request: SplitInstallRequest) = mManager.startInstall(request)

    fun isModuleInstalled(module: String): Boolean = mManager.installedModules.contains(module)
}