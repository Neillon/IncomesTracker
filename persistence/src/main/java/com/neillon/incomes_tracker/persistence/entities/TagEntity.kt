package com.neillon.incomes_tracker.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "tag",
    foreignKeys = [
        ForeignKey(
            entity = IncomeEntity::class,
            parentColumns = ["income_id"],
            childColumns = ["tag_income_id"],
            onDelete = CASCADE,
            onUpdate = CASCADE
        )
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