package com.neillon.incomes_tracker.domain.contracts

interface Repository<T> {
    fun insert(entity: T): T
    fun listAll(): List<T>
    fun remove(entity: T)
    fun update(entity: T)
}