package com.neillon.incomes_tracker.persistence.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.Repository
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.extensions.toDomain
import com.neillon.incomes_tracker.persistence.extensions.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IncomeRepository constructor(
    private var database: IncomeDatabase
) : Repository<Income> {

    private val dao = database.incomeDao()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun insert(income: Income): Flow<Income> = flow {
        dao.insertAndReturn(income.toEntity()).toDomain()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun listAll(): Flow<List<Income>> = flow {
        dao.getAll().toDomain()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getById(id: Long): Flow<Income> = flow {
        dao.getById(id).toDomain()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun remove(income: Income) =
        dao.remove(income.toEntity())

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun update(income: Income): Flow<Income> = flow {
        dao.updateAndReturn(income.toEntity()).toDomain()
    }

}