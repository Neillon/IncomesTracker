package com.neillon.incomes_tracker.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "income")
data class IncomeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "income_id")
    var id: Long? = null,

    @ColumnInfo(name = "income_description")
    var description: String,


    @ColumnInfo(name = "income_value")
    var value: Double,

    @ColumnInfo(name = "income_date")
    var date: LocalDate
)