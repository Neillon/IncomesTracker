package com.neillon.incomes_tracker.persistence.di

import com.neillon.incomes_tracker.persistence.repositories.IncomeRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface RepositoryComponent {
    fun incomeRepsoitory(): IncomeRepository
}