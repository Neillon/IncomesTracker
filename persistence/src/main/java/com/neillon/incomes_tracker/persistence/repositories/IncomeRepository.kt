package com.neillon.incomes_tracker.persistence.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.IIncomeRepository
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.extensions.toDomain
import com.neillon.incomes_tracker.persistence.extensions.toEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class IncomeRepository constructor(
    var database: IncomeDatabase,
    var dispatcher: CoroutineDispatcher
) : IIncomeRepository {

    private val dao = database.incomeDao()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun insert(income: Income): Income =
        dao.insertAndReturn(income.toEntity()).toDomain()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun listAll(): Flow<List<Income>> = flow {
        val data = dao.getAll().toDomain()
        emit(data)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getById(id: Long): Income = dao.getById(id).toDomain()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun remove(income: Income) =
        dao.remove(income.toEntity())

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun update(income: Income): Income =
        dao.updateAndReturn(income.toEntity()).toDomain()

}