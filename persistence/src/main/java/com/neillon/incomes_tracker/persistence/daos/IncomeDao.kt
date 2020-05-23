package com.neillon.incomes_tracker.persistence.daos

import androidx.room.*
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.models.IncomeWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(income: IncomeEntity): Long

    @Transaction
    @Query("SELECT * FROM income WHERE income_id = :id")
    suspend fun getById(id: Long): Flow<IncomeWithTags>?

    @Transaction
    @Query("SELECT * FROM income")
    suspend fun getAll(): Flow<List<IncomeWithTags>>?

    @Update
    suspend fun update(vararg incomes: IncomeEntity): Long

    @Delete
    suspend fun remove(vararg incomes: IncomeEntity): Long
}
