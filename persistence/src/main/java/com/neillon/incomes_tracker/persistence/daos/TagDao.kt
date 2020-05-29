package com.neillon.incomes_tracker.persistence.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.neillon.incomes_tracker.persistence.entities.TagEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TagDao : BaseDao<TagEntity> {

    @Transaction
    @Query("SELECT * FROM tag WHERE tag_id = :id")
    abstract suspend fun getById(id: Int): Flow<TagEntity>

    @Transaction
    @Query("SELECT * FROM income")
    abstract suspend fun getAll(): Flow<List<TagEntity>>

    @Transaction
    open suspend fun updateAndReturn(entity: TagEntity): Flow<TagEntity> {
        val id = update(entity)
        return getById(id)
    }

    @Transaction
    open suspend fun insertAndReturn(entity: TagEntity): Flow<TagEntity> {
        val id = insert(entity)
        return getById(id)
    }
}