package com.neillon.incomes_tracker.persistence.di

import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.Repository
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.repositories.IncomeRepository
import com.neillon.incomes_tracker.persistence.repositories.TagRepository
import org.koin.dsl.module

object PersistenceModule {

    val persistenceModule = module {
        single {
            IncomeDatabase.getInstance(context = get())
        }

        single {
            IncomeRepository(database = get()) as Repository<Income>
        }

        single {
            TagRepository(database = get()) as Repository<Income>
        }
    }

}
