package com.neillon.incomes_tracker.usecase.di

import com.neillon.incomes_tracker.usecase.incomes.SaveIncomeUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

object UseCaseModule {

    val useCaseModule = module {

        factory {
            SaveIncomeUseCase(
                incomeRepository = get(),
                tagRepository = get()
            )
        }

    }
}