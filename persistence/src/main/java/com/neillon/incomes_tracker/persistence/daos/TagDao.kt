package com.neillon.incomes_tracker.persistence.daos

import androidx.room.*
import com.neillon.incomes_tracker.persistence.entities.TagEntity

@Dao
interface TagDao: BaseDao<TagEntity> {

    @Query("SELECT * FROM income")
    suspend fun getAll(): List<TagEntity>?
}