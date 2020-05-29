package com.neillon.incomes_tracker.usecase

import androidx.lifecycle.LiveData
import kotlin.coroutines.CoroutineContext

abstract class UseCase<in Params, Result>(
    private val coroutineContext: CoroutineContext
) {
    abstract fun invoke(params: Params? = null): LiveData<Result>
}