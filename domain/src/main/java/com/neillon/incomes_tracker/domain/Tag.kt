package com.neillon.incomes_tracker.domain

data class Tag (
    var id: Long? = null,
    var description: String,
    var incomeId: Long
)