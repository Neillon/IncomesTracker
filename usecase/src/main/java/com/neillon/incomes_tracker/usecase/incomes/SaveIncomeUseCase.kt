package com.neillon.incomes_tracker.usecase.incomes

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.Repository
import com.neillon.incomes_tracker.usecase.UseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class SaveIncomeUseCase(
    private val coroutineContext: CoroutineContext,
    private val incomeRepository: Repository<Income>
): UseCase<SaveIncomeUseCase.Params, Income>(coroutineContext) {

    data class Params(var income: Income)

    override fun invoke(params: Params?): LiveData<Income> {
        CoroutineScope(coroutineContext + Dispatchers.IO).launch {
            incomeRepository.insert(params!!.income)
                .map { it!! }
                .asLiveData(coroutineContext)
        }
    }
}
