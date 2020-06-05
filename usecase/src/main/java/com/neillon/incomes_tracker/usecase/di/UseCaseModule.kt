package com.neillon.incomes_tracker.usecase.di

import androidx.lifecycle.ViewModel
import com.neillon.incomes_tracker.usecase.CoroutineUseCase
import com.neillon.incomes_tracker.usecase.incomes.ListAllIncomesUseCase
import com.neillon.incomes_tracker.usecase.incomes.SaveIncomeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent.inject

object UseCaseModule {

    val useCaseModule = module {

        factory {
            SaveIncomeUseCase(
                incomeRepository = get(),
                tagRepository = get()
            )
        }

        factory {
            ListAllIncomesUseCase(
                incomeRepository = get(),
                coroutineContext = Dispatchers.IO
            )
        }

    }
}