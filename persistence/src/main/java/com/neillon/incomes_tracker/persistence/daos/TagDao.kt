package com.neillon.incomes_tracker.persistence.daos

import androidx.room.*
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.TagEntity
import com.neillon.incomes_tracker.persistence.entities.models.IncomeWithTags
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao: BaseDao<TagEntity> {

    @Transaction
    @Query("SELECT * FROM tag WHERE tag_id = :id")
    suspend fun getById(id: Long): Flow<TagEntity>

    @Query("SELECT * FROM income")
    suspend fun getAll(): Flow<List<TagEntity>>

    @Transaction
    suspend fun updateAndReturn(entity: TagEntity): Flow<TagEntity> {
        val id = update(entity)
        return getById(id)
    }

    @Transaction
    suspend fun insertAndReturn(entity: TagEntity): Flow<TagEntity> {
        val id = insert(entity)
        return getById(id)
    }
}