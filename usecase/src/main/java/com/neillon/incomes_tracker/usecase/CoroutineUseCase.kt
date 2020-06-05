package com.neillon.incomes_tracker.usecase

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class CoroutineUseCase<in Params, Result> {
    abstract fun invoke(params: Params? = null, coroutineScope: CoroutineScope): MutableLiveData<Result>
}