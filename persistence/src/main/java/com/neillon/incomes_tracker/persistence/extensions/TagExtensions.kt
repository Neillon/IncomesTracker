package com.neillon.incomes_tracker.persistence.extensions

import com.neillon.incomes_tracker.domain.Tag
import com.neillon.incomes_tracker.persistence.entities.TagEntity

fun TagEntity.toDomain() =
    Tag(
        id = this.id ?: 0,
        description = this.description,
        incomeId = this.incomeId
    )

fun Tag.toEntity() =
    TagEntity(
        id = this.id,
        description = this.description,
        incomeId = this.incomeId
    )

fun List<TagEntity>.toDomain() = this.map { it.toDomain() }