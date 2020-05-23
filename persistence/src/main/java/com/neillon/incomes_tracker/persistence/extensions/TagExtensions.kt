package com.neillon.incomes_tracker.persistence.extensions

import com.neillon.incomes_tracker.domain.Tag
import com.neillon.incomes_tracker.persistence.entities.TagEntity

fun TagEntity.toDomain() =
    Tag(
        id = this.id,
        description = this.description
    )