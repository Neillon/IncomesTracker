package com.neillon.incomes_tracker.usecase.incomes

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.Tag
import com.neillon.incomes_tracker.domain.contracts.IIncomeRepository
import com.neillon.incomes_tracker.domain.contracts.ITagRepository
import com.neillon.incomes_tracker.domain.contracts.Repository
import com.neillon.incomes_tracker.usecase.UseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.CoroutineContext

class SaveIncomeUseCase(
    override val coroutineContext: CoroutineContext,
    private val incomeRepository: IIncomeRepository,
    private val tagRepository: ITagRepository
) : UseCase<SaveIncomeUseCase.Params, Income>(coroutineContext) {

    data class Params(var income: Income)

    override fun invoke(params: Params?): LiveData<Income> =
        liveData(coroutineContext + Dispatchers.IO) {
            val income = params!!.income
            incomeRepository.insert(income)
                .zip(tagRepository.insert(params.income.tags)) { income, tags ->
                    income.tags = tags
                }
                .asLiveData(coroutineContext)
        }
}
