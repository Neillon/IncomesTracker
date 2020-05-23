package com.neillon.incomes_tracker.persistence.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.TagEntity

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(income: T): Long

    @Update
    suspend fun update(vararg incomes: T): T

    @Delete
    suspend fun remove(vararg incomes: T)
}