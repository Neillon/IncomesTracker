package com.neillon.incomes_tracker.domain.contracts

import com.neillon.incomes_tracker.domain.Tag
import kotlinx.coroutines.flow.Flow

interface ITagRepository : Repository<Tag> {
    suspend fun insert(tags: List<Tag>): List<Tag>
}
