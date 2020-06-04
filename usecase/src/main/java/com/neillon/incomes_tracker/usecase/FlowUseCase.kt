package com.neillon.incomes_tracker.usecase

import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

abstract class FlowUseCase<in Params, Result>(
    open val coroutineContext: CoroutineContext
) {
    abstract fun invoke(params: Params? = null): Flow<Result>
}