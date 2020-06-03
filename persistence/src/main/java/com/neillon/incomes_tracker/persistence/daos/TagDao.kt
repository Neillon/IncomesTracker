package com.neillon.incomes_tracker.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.neillon.incomes_tracker.persistence.entities.TagEntity

@Dao
abstract class TagDao : BaseDao<TagEntity> {

    @Insert
    abstract suspend fun insert(vararg entity:TagEntity): List<Long>

    @Transaction
    @Query("SELECT * FROM tag WHERE tag_id in (:ids)")
    abstract suspend fun getById(ids: List<Long>): List<TagEntity>

    @Transaction
    @Query("SELECT * FROM tag WHERE tag_id = :id")
    abstract suspend fun getById(id: Long): TagEntity

    @Transaction
    open suspend fun updateAndReturn(entity: TagEntity): TagEntity {
        update(entity)
        return getById(entity.id ?: 0)
    }

    @Transaction
    open suspend fun insertAndReturn(vararg entity: TagEntity): List<TagEntity> {
        val ids = insert(*entity)
        return getById(ids)
    }

}