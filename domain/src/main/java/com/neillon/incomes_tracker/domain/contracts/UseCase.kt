package com.neillon.incomes_tracker.domain.contracts

interface UseCase<in TEntity, out TOutput> {
    fun execute(entity: TEntity): TOutput
}