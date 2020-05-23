package com.neillon.incomes_tracker.domain.contracts

import kotlinx.coroutines.flow.Flow

interface Repository<T> {
    suspend fun insert(entity: T): T
    suspend fun listAll(): Flow<List<T>?>
    suspend fun getById(id: Long): Flow<T?>
    suspend fun remove(entity: T)
    suspend fun update(entity: T)
}