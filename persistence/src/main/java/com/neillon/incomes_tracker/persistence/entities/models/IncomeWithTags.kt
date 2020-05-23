package com.neillon.incomes_tracker.persistence.entities.models

import androidx.room.Embedded
import androidx.room.Relation
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.TagEntity

data class IncomeWithTags(
    @Embedded var income: IncomeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "incomeId"
    )
    var tags: List<TagEntity>
)