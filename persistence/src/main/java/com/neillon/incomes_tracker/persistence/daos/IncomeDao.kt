package com.neillon.incomes_tracker.persistence.daos

import androidx.room.*
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.models.IncomeWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao: BaseDao<IncomeEntity> {

    @Transaction
    @Query("SELECT * FROM income WHERE income_id = :id")
    suspend fun getById(id: Long): Flow<IncomeWithTags>?

    @Transaction
    @Query("SELECT * FROM income")
    suspend fun getAll(): Flow<List<IncomeWithTags>>?
}
