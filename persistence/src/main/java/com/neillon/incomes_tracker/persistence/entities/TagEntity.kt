package com.neillon.incomes_tracker.persistence.entities

import androidx.room.*
import androidx.room.ForeignKey.CASCADE

@Entity(
    tableName = "tag",
    foreignKeys = [
        ForeignKey(entity = IncomeEntity::class,
                   parentColumns = ["income_id"],
                   childColumns = ["tag_income_id"],
                   onDelete = CASCADE,
                   onUpdate = CASCADE)
    ]
)
data class TagEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tag_id")
    var id: Long,

    @ColumnInfo(name = "tag_description")
    var description: String,

    @ColumnInfo(name = "tag_income_id", index = true)
    var incomeId: Long
)