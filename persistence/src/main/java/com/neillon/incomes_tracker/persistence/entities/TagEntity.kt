package com.neillon.incomes_tracker.persistence.entities

import androidx.room.*

@Entity(tableName = "tag")
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tag_id")
    var id: Long,

    @ColumnInfo(name = "tag_description")
    var description: String,

    @ColumnInfo(name = "tag_income_id")
    var incomeId: Long
)