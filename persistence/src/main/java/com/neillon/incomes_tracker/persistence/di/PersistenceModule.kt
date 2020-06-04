package com.neillon.incomes_tracker.persistence.di

import com.neillon.incomes_tracker.domain.contracts.IIncomeRepository
import com.neillon.incomes_tracker.domain.contracts.ITagRepository
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.repositories.IncomeRepository
import com.neillon.incomes_tracker.persistence.repositories.TagRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

object PersistenceModule {

    val persistenceModule = module {
        single {
            IncomeDatabase.getInstance(context = get())
        }

        single {
            IncomeRepository(database = get(), dispatcher = Dispatchers.IO) as IIncomeRepository
        }

        single {
            TagRepository(database = get()) as ITagRepository
        }
    }

}
