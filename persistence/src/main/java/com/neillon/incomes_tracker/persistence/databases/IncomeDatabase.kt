package com.neillon.incomes_tracker.persistence.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.neillon.incomes_tracker.persistence.constants.RoomConstants
import com.neillon.incomes_tracker.persistence.daos.IncomeDao
import com.neillon.incomes_tracker.persistence.daos.TagDao
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.TagEntity

@Database(
    entities = [
        TagEntity::class,
        IncomeEntity::class
    ],
    version = RoomConstants.ROOM_VERSION,
    exportSchema = false
)
abstract class IncomeDatabase: RoomDatabase() {

    abstract fun incomeDao(): IncomeDao
    abstract fun tagDao(): TagDao

    companion object {
        private lateinit var instance: IncomeDatabase

        fun getInstance(context: Context): IncomeDatabase {
            if (instance != null) {
                return instance
            }

            instance = Room
                .databaseBuilder(context, IncomeDatabase::class.java, "incomes.db")
                .build()

            return instance as IncomeDatabase
        }

    }
}