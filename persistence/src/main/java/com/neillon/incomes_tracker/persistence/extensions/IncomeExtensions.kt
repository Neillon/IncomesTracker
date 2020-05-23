package com.neillon.incomes_tracker.persistence.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.persistence.entities.IncomeEntity
import com.neillon.incomes_tracker.persistence.entities.TagEntity
import com.neillon.incomes_tracker.persistence.entities.models.IncomeWithTags

@RequiresApi(Build.VERSION_CODES.O)
fun Income.toEntity(): IncomeEntity =
    IncomeEntity(
        id = this.id,
        date = this.date,
        description = this.description,
        value = this.value
    )

fun IncomeEntity.toDomain(): Income =
    Income(
        id = this.id,
        description = this.description,
        value = this.value,
        date = this.date,
        tags = emptyList()
    )

@RequiresApi(Build.VERSION_CODES.O)
fun IncomeWithTags.toDomain(): Income =
    Income(
        id = this.income.id,
        date = this.income.date,
        description = this.income.description,
        value = this.income.value,
        tags = this.tags.map { it.toDomain() }
    )
