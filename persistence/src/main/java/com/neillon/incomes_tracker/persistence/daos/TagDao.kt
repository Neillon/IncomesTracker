package com.neillon.incomes_tracker.persistence.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.neillon.incomes_tracker.persistence.entities.TagEntity

@Dao
abstract class TagDao : BaseDao<TagEntity> {

    @Transaction
    @Query("SELECT * FROM tag WHERE tag_id = :id")
    abstract suspend fun getById(id: Long): TagEntity

    @Transaction
    @Query("SELECT * FROM tag")
    abstract suspend fun getAll(): List<TagEntity>

    @Transaction
    open suspend fun updateAndReturn(entity: TagEntity): TagEntity {
        update(entity)
        return getById(entity.id ?: 0)
    }

    @Transaction
    open suspend fun insertAndReturn(entity: TagEntity): TagEntity {
        val id = insert(entity)
        return getById(id)
    }
}