package com.neillon.incomes_tracker.persistence.daos

import androidx.room.*
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.TagEntity

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T): Int

    @Update
    fun update(vararg entity: T): Int

    @Delete
    fun remove(vararg entity: T)

}