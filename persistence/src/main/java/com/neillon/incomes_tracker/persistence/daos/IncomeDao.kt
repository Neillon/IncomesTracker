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
    abstract suspend fun getById(id: Long): IncomeWithTags

    @Transaction
    @Query("SELECT * FROM Income")
    abstract suspend fun getAll(): List<IncomeWithTags>

    @Transaction
    open suspend fun updateAndReturn(entity: IncomeEntity): IncomeWithTags {
        update(entity)
        return getById(entity.id)
    }

    @Transaction
    open suspend fun insertAndReturn(entity: IncomeEntity): IncomeWithTags {
        val id = insert(entity)
        return getById(id)
    }
}
