package com.neillon.incomes_tracker.router

import com.neillon.incomes_tracker.persistence.di.PersistenceModule.persistenceModule
import com.neillon.incomes_tracker.router.constants.BASE_PACKAGE
import com.neillon.incomes_tracker.router.constants.DASHBOARD_ENTRYPOINT_CLASSNAME
import com.neillon.incomes_tracker.router.constants.HOME_ENTRYPOINT_CLASSNAME
import com.neillon.incomes_tracker.usecase.di.UseCaseModule.useCaseModule
import com.neillon.ioncomes_tracker.presentation.di.PresentationModule.presentationModule
import org.koin.core.module.Module

sealed class Feature() {
    val entryPackageName: String =
        BASE_PACKAGE

    abstract val entryActivityName: String
    abstract val modules: List<Module>

    object Dashboard : Feature() {
        override val entryActivityName: String =
            DASHBOARD_ENTRYPOINT_CLASSNAME
        override val modules: List<Module>
            get() = listOf(
                persistenceModule,
                useCaseModule,
                presentationModule
            )
    }

    object Home : Feature() {
        override val entryActivityName: String =
            HOME_ENTRYPOINT_CLASSNAME
        override val modules: List<Module>
            get() = emptyList()
    }
}