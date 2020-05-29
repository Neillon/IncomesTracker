package com.neillon.incomes_tracker.usecase.incomes

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.Repository
import com.neillon.incomes_tracker.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

class SaveIncomeUseCase(
    private val coroutineContext: CoroutineContext,
    private val incomeRepository: Repository<Income>
) : UseCase<SaveIncomeUseCase.Params, Income>(coroutineContext) {

    data class Params(var income: Income)

    override fun invoke(params: Params?): LiveData<Income> =
        liveData(coroutineContext + Dispatchers.IO) {
            incomeRepository.insert(params!!.income)
                .map { it!! }
                .collect {
                    emit(it)
                }
        }
}
