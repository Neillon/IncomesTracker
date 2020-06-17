package com.neillon.ioncomes_tracker.presentation.extensions

import com.neillon.incomes_tracker.domain.Income
import com.neillon.ioncomes_tracker.presentation.binding.IncomeBinding

fun Income.toBind() = IncomeBinding(
    id = this.id ?: 0,
    date = this.date.toString(),
    description = this.description,
    value = this.value
)