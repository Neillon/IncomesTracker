package com.neillon.incomes_tracker.persistence.daos

import androidx.room.*
import com.neillon.incomes_tracker.persistence.entities.TagEntity

@Dao
interface TagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(income: TagEntity): Long

    @Query("SELECT * FROM income")
    suspend fun getAll(): List<TagEntity>?

    @Update
    suspend fun update(vararg incomes: TagEntity): Long

    @Delete
    suspend fun remove(vararg incomes: TagEntity): Long
}