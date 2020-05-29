package com.neillon.incomes_tracker.persistence.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.models.IncomeWithTags
import kotlinx.coroutines.flow.Flow

@Dao
abstract class IncomeDao : BaseDao<IncomeEntity> {

    @Transaction
    @Query("SELECT * FROM Income WHERE income_id = :id")
    abstract suspend fun getById(id: Int): Flow<IncomeWithTags>

    @Transaction
    @Query("SELECT * FROM Income")
    abstract suspend fun getAll(): Flow<List<IncomeWithTags>>

    @Transaction
    open suspend fun updateAndReturn(entity: IncomeEntity): Flow<IncomeWithTags> {
        val id = update(entity)
        return getById(id)
    }

    @Transaction
    open suspend fun insertAndReturn(entity: IncomeEntity): Flow<IncomeWithTags> {
        val id = insert(entity)
        return getById(id)
    }
}
