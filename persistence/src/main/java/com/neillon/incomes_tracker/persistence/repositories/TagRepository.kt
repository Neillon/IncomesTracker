package com.neillon.incomes_tracker.persistence.repositories

import com.neillon.incomes_tracker.domain.Tag
import com.neillon.incomes_tracker.domain.contracts.ITagRepository
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.extensions.toDomain
import com.neillon.incomes_tracker.persistence.extensions.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class TagRepository constructor(
    var database: IncomeDatabase
) : ITagRepository {

    private val dao = database.tagDao()

    override suspend fun insert(tag: Tag): Tag =
        throw NotImplementedError("Insert tag was not implemented yet.")

    override suspend fun insert(tags: List<Tag>): List<Tag> {
        val tagsToInsert = tags.map { it.toEntity() }.toTypedArray()
        return dao.insertAndReturn(*tagsToInsert).toDomain()
    }

    override suspend fun listAll(): Flow<List<Tag>> =
        throw NotImplementedError("Get all tags was not implemented yet.")

    override suspend fun getById(id: Long): Tag = dao.getById(id).toDomain()

    override suspend fun remove(tag: Tag) = dao.remove(tag.toEntity())

    override suspend fun update(tag: Tag): Tag = dao.updateAndReturn(tag.toEntity()).toDomain()

}