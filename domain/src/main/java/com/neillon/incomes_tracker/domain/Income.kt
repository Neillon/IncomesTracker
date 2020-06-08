package com.neillon.incomes_tracker.domain

import java.time.LocalDate

data class Income(
    var id: Long? = null,
    var description: String,
    var value: Double,
    var date: LocalDate,
    var tags: MutableList<Tag>
)