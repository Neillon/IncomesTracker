package com.neillon.incomes_tracker.dashboard.ui.incomes.fragments.dialogs

import android.text.Editable
import java.math.BigDecimal

fun Editable.asBigDecimal(): BigDecimal {
    val replaceable = String.format("[%s,.\\s]", currencySymbol)

    val cleanString = this.replace(replaceable.toRegex(), "")

    return try {
        BigDecimal(cleanString).setScale(
            2, BigDecimal.ROUND_FLOOR
        ).divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
    } catch (e: NumberFormatException) {
        BigDecimal(0)
    }

}