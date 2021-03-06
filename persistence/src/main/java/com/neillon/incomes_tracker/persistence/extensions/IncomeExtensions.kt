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
        id = this.id ?: 0,
        description = this.description,
        value = this.value,
        date = this.date,
        tags = mutableListOf()
    )

@RequiresApi(Build.VERSION_CODES.O)
fun IncomeWithTags.toDomain(): Income =
    Income(
        id = this.income.id ?: 0,
        date = this.income.date,
        description = this.income.description,
        value = this.income.value,
        tags = this.tags.map { it.toDomain() }.toMutableList()
    )

@RequiresApi(Build.VERSION_CODES.O)
fun List<IncomeWithTags>.toDomain() = this.map { it.toDomain() }
