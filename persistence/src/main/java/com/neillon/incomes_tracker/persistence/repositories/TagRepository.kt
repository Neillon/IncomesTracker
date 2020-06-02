package com.neillon.incomes_tracker.persistence.repositories

import com.neillon.incomes_tracker.domain.Tag
import com.neillon.incomes_tracker.domain.contracts.Repository
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.extensions.toDomain
import com.neillon.incomes_tracker.persistence.extensions.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TagRepository constructor(
    var database: IncomeDatabase,
    var dispatcher: CoroutineDispatcher
) : Repository<Tag> {

    private val dao = database.tagDao()

    override suspend fun insert(tag: Tag): Flow<Tag> = flow {
            val data = dao.insertAndReturn(tag.toEntity()).toDomain()
            emit(data)
        }
        .flowOn(dispatcher)


    override suspend fun listAll(): Flow<List<Tag>> = flow {
            val data = dao.getAll().toDomain()
            emit(data)
        }
        .flowOn(dispatcher)

    override suspend fun getById(id: Long): Flow<Tag> = flow {
            val data = dao.getById(id).toDomain()
            emit(data)
        }
        .flowOn(dispatcher)

    override suspend fun remove(tag: Tag) = dao.remove(tag.toEntity())

    override suspend fun update(tag: Tag): Flow<Tag> = flow {
            val data = dao.updateAndReturn(tag.toEntity()).toDomain()
            emit(data)
        }
        .flowOn(dispatcher)

}