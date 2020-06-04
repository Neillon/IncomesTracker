package com.neillon.incomes_tracker.usecase.incomes

import androidx.lifecycle.MutableLiveData
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.IIncomeRepository
import com.neillon.incomes_tracker.domain.contracts.ITagRepository
import com.neillon.incomes_tracker.usecase.CoroutineUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

class SaveIncomeUseCase(
    override val coroutineScope: CoroutineScope,
    private val incomeRepository: IIncomeRepository,
    private val tagRepository: ITagRepository
) : CoroutineUseCase<SaveIncomeUseCase.Params, Income>(coroutineScope) {

    data class Params(var income: Income)

    override fun invoke(params: Params?): MutableLiveData<Income> {
        val result = MutableLiveData<Income>()
        val income = params!!.income

        runBlocking(coroutineScope.coroutineContext) {
            val savedIncome = incomeRepository.insert(income)

            if (params.income.tags.isNotEmpty()) {
                val savedTags = tagRepository.insert(params.income.tags)
                savedIncome.tags = savedTags
            }
            result.value = savedIncome
            result
        }

        return result
    }


}
