package com.neillon.incomes_tracker.router

sealed class Feature() {
    abstract val entryActivityName: String
    val entryPackageName: String = BASE_PACKAGE

    object Login : Feature() {
        override val entryActivityName: String = LOGIN_ENTRYPOINT_CLASSNAME
    }

    object Dashboard : Feature() {
        override val entryActivityName: String = DASHBOARD_ENTRYPOINT_CLASSNAME
    }

    object Home: Feature() {
        override val entryActivityName: String = HOME_ENTRYPOINT_CLASSNAME
    }
}