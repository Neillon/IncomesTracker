package com.neillon.ioncomes_tracker.presentation.viewmodels

import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.usecase.incomes.ListAllIncomesUseCase
import com.neillon.incomes_tracker.usecase.incomes.SaveIncomeUseCase
import com.neillon.ioncomes_tracker.presentation.asLiveData
import com.neillon.ioncomes_tracker.presentation.extensions.toBind
import com.neillon.ioncomes_tracker.presentation.types.IncomeRow
import com.neillon.ioncomes_tracker.presentation.types.IncomeRowType
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.*

class IncomeViewModel : ViewModel() {
    private val saveIncomeUseCase by inject(SaveIncomeUseCase::class.java)
    private val listAllIncomesUseCase by inject(ListAllIncomesUseCase::class.java)

    private val _incomesList: MutableLiveData<MutableList<Income>> by lazy {
        MutableLiveData<MutableList<Income>>().apply {
            value = mutableListOf()
        }
    }
    val incomesList = Transformations.map(_incomesList) {
        transformData(it)
    }

    private val _loadingBalance = MutableLiveData(false)
    val loadingBalance = _loadingBalance.asLiveData

    val balance = Transformations.map(_incomesList) { incomes ->
        val locale = Locale("PT", "BR")
        val total = incomes.map { it.value }.sum()
        NumberFormat.getCurrencyInstance(locale).format(total)
    }


    fun saveIncome(income: Income, lifecycleOwner: LifecycleOwner) {
        val newIncome =
            saveIncomeUseCase.invoke(SaveIncomeUseCase.Params(income = income), viewModelScope)
        newIncome.observe(lifecycleOwner, Observer {
            _incomesList.value?.add(it)
        })
    }

    fun listAll() {
        _loadingBalance.value = true
        viewModelScope.launch {
            listAllIncomesUseCase.invoke(ListAllIncomesUseCase.Nothing())
                .collect {
                    val incomes = _incomesList.value
                    incomes?.addAll(it)
                    _incomesList.value = incomes

                    _loadingBalance.value = false
                }
        }
    }

    private fun transformData(incomes: MutableList<Income>): MutableList<IncomeRow> {
        val result = mutableListOf<IncomeRow>()
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val groups = incomes.groupBy { item -> item.date }
        for (group in groups) {
            result.add(
                IncomeRow(
                    type = IncomeRowType.GROUP,
                    groupHeader = group.key.format(dateFormatter),
                    item = null
                )
            )
            group.value.forEach {
                result.add(
                    IncomeRow(
                        type = IncomeRowType.ITEM,
                        groupHeader = null,
                        item = it.toBind()
                    )
                )
            }
        }

        return result
    }

    companion object {
        const val TAG = "IncomeViewModel"
    }
}