package com.neillon.incomes_tracker.domain

import java.util.*

data class Income(
    var description: String,
    var value: Double,
    var date: Date,
    var tags: List<Tag>
)