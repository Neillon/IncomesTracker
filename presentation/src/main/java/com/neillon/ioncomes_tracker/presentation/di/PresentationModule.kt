package com.neillon.ioncomes_tracker.presentation.di

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.neillon.ioncomes_tracker.presentation.viewmodels.IncomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object PresentationModule {
    val presentationModule = module {

        viewModel { (owner: ViewModelStoreOwner) ->
            // val viewModelFactory = IncomeViewModelFactory(saveIncomeUseCase = get())
            ViewModelProvider(owner).get(IncomeViewModel::class.java) as IncomeViewModel
        }

    }
}