package com.neillon.incomes_tracker.usecase

import androidx.lifecycle.MutableLiveData
import kotlin.coroutines.CoroutineContext

abstract class UseCase<in Params, Result>(
    open val coroutineContext: CoroutineContext?
) {
    abstract suspend fun invoke(params: Params? = null): MutableLiveData<Result>
}