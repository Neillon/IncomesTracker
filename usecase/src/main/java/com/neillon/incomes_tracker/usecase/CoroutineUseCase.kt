package com.neillon.incomes_tracker.usecase

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

abstract class CoroutineUseCase<in Params, Result>(
    open val coroutineScope: CoroutineScope
) {
    abstract fun invoke(params: Params? = null): MutableLiveData<Result>

    fun destroy() {
        coroutineScope.cancel()
    }
}