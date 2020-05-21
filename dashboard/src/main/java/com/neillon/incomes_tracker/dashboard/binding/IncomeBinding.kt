package com.neillon.incomes_tracker.dashboard.binding

import java.util.*

data class IncomeBinding(
    var id: Long,
    var description: String,
    var value: Double,
    var date: String
)