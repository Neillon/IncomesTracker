package com.neillon.incomes_tracker.dashboard.adapterType

import com.neillon.incomes_tracker.dashboard.binding.IncomeBinding

data class IncomeRow(
    var type: IncomeRowType,
    var item: IncomeBinding?,
    var header: String?
)