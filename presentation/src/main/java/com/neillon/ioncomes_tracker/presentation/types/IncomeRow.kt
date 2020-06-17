package com.neillon.ioncomes_tracker.presentation.types

import com.neillon.ioncomes_tracker.presentation.binding.IncomeBinding

data class IncomeRow(
    var type: IncomeRowType,
    var item: IncomeBinding?,
    var groupHeader: String?
)