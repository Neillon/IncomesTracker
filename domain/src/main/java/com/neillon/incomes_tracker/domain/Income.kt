package com.neillon.incomes_tracker.domain

import java.time.LocalDate

data class Income(
    var id: Long,
    var description: String,
    var value: Double,
    var date: LocalDate,
    var tags: List<Tag>
)