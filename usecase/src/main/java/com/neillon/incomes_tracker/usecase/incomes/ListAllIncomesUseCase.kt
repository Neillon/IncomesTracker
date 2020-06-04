package com.neillon.incomes_tracker.usecase.incomes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.IIncomeRepository
import com.neillon.incomes_tracker.usecase.UseCase
import kotlin.coroutines.CoroutineContext

class ListAllIncomesUseCase(
    override val coroutineContext: CoroutineContext,
    private val incomeRepository: IIncomeRepository
) : UseCase<ListAllIncomesUseCase.Nothing, List<Income>>(coroutineContext) {

    class Nothing

    override suspend fun invoke(params: Nothing?): MutableLiveData<List<Income>> {
        val result = MutableLiveData<List<Income>>()
        val incomes = incomeRepository.listAll().asLiveData(coroutineContext)
        result.value = incomes.value

        return result
    }
}