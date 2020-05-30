package com.neillon.incomes_tracker.persistence.repositories

import com.neillon.incomes_tracker.domain.Tag
import com.neillon.incomes_tracker.domain.contracts.Repository
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.extensions.toDomain
import com.neillon.incomes_tracker.persistence.extensions.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TagRepository constructor(
    private val database: IncomeDatabase
) : Repository<Tag> {
    private val dao = database.tagDao()

    override suspend fun insert(tag: Tag): Flow<Tag?> =
        dao.insertAndReturn(tag.toEntity()).map { it.toDomain() }

    override suspend fun listAll(): Flow<List<Tag>?> =
        dao.getAll().map { it!!.toDomain() }

    override suspend fun getById(id: Int): Flow<Tag?> =
        dao.getById(id).map { it.toDomain() }

    override suspend fun remove(tag: Tag) =
        dao.remove(tag.toEntity())

    override suspend fun update(tag: Tag): Flow<Tag> =
        dao.updateAndReturn(tag.toEntity()).map { it.toDomain() }

}