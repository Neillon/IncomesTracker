package com.neillon.incomes_tracker.persistence.daos

import androidx.room.*
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.TagEntity

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Long

    @Update
    fun update(vararg entity: T): Long

    @Delete
    fun remove(vararg entity: T)

}