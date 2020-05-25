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
    @Query("SELECT * FROM income WHERE income_id = :id")
    abstract suspend fun getById(id: Long): Flow<IncomeWithTags>

    @Transaction
    @Query("SELECT * FROM income")
    abstract suspend fun getAll(): Flow<List<IncomeWithTags>>

    @Transaction
    suspend fun updateAndReturn(entity: IncomeEntity): Flow<IncomeWithTags> {
        val id = update(entity)
        return getById(id)
    }

    @Transaction
    suspend fun insertAndReturn(entity: IncomeEntity): Flow<IncomeWithTags> {
        val id = insert(entity)
        return getById(id)
    }
}
