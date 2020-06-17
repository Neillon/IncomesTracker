package com.neillon.ioncomes_tracker.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

val <T>MutableLiveData<T>.asLiveData: LiveData<T>
    get() = this