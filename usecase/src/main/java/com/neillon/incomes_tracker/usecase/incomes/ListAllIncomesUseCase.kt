package com.neillon.incomes_tracker.usecase.incomes

import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.contracts.IIncomeRepository
import com.neillon.incomes_tracker.usecase.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class ListAllIncomesUseCase(
    override val coroutineContext: CoroutineContext,
    private val incomeRepository: IIncomeRepository
) : FlowUseCase<ListAllIncomesUseCase.Nothing, List<Income>>(coroutineContext) {

    class Nothing

    override fun invoke(params: Nothing?): Flow<List<Income>> =
        incomeRepository.listAll()
            .flowOn(coroutineContext)
}