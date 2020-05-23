package com.neillon.incomes_tracker.persistence.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.Repository
import com.neillon.incomes_tracker.persistence.databases.IncomeDatabase
import com.neillon.incomes_tracker.persistence.extensions.toDomain
import com.neillon.incomes_tracker.persistence.extensions.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IncomeRepository(
    private var database: IncomeDatabase
) : Repository<Income> {

    private val dao = database.incomeDao()

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun insert(entity: Income): Flow<Income?> {
        val id = dao.insert(entity.toEntity())
        return dao.getById(id)!!.map { it.toDomain() }
    }

    override suspend fun listAll(): Flow<List<Income>> =
        dao.getAll().map { it.toDomain() }

    override suspend fun remove(entity: Income) {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: Income) {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): Flow<Income?> = dao.getById(id).map { it.toDomain() }
}