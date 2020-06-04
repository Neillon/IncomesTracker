package com.neillon.incomes_tracker.usecase.di

import com.neillon.incomes_tracker.usecase.incomes.ListAllIncomesUseCase
import com.neillon.incomes_tracker.usecase.incomes.SaveIncomeUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

object UseCaseModule {

    val useCaseModule = module {

        factory {
            SaveIncomeUseCase(
                incomeRepository = get(),
                tagRepository = get(),
                coroutineScope = CoroutineScope(Dispatchers.IO)
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