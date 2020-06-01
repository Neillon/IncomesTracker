package com.neillon.incomes_tracker.persistence.repositories

import com.neillon.incomes_tracker.domain.Tag
import com.neillon.incomes_tracker.domain.contracts.Repository
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.extensions.toDomain
import com.neillon.incomes_tracker.persistence.extensions.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TagRepository constructor(
    private val database: IncomeDatabase
) : Repository<Tag> {

    private val dao = database.tagDao()

    override suspend fun insert(tag: Tag): Flow<Tag> = flow {
        dao.insertAndReturn(tag.toEntity()).toDomain()
    }

    override suspend fun listAll(): Flow<List<Tag>> = flow {
        dao.getAll().toDomain()
    }

    override suspend fun getById(id: Long): Flow<Tag> = flow {
        dao.getById(id).toDomain()
    }

    override suspend fun remove(tag: Tag) = dao.remove(tag.toEntity())

    override suspend fun update(tag: Tag): Flow<Tag> = flow {
        dao.updateAndReturn(tag.toEntity()).toDomain()
    }

}