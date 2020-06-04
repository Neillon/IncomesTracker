package com.neillon.incomes_tracker.usecase.incomes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.IIncomeRepository
import com.neillon.incomes_tracker.domain.contracts.ITagRepository
import com.neillon.incomes_tracker.usecase.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SaveIncomeUseCase(
    private val incomeRepository: IIncomeRepository,
    private val tagRepository: ITagRepository
) : UseCase<SaveIncomeUseCase.Params, Income>(null) {

    data class Params(var income: Income)

    override suspend fun invoke(params: Params?): MutableLiveData<Income> {
        val result = MutableLiveData<Income>()
        val income = params!!.income

        val savedIncome = incomeRepository.insert(income)

        if (params.income.tags.isNotEmpty()) {
            val savedTags = tagRepository.insert(params.income.tags)
            savedIncome.tags = savedTags
        }

        result.value = savedIncome
        return result
    }
}
