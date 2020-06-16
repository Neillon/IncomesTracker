package com.neillon.incomes_tracker.dashboard.ui.incomes.fragments.dialogs

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.android.synthetic.main.fragment_dialog_new_income_bottom_sheet.*
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


val currencySymbol: String
    get() = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).currency.symbol


fun EditText.addMoneyWatcher(
    initialValue: Double? = 0.0,
    onInputDouble: (Double) -> Unit
) {
    val locale = Locale("pt", "BR")
    val editText = this

    val parsed = parseToBigDecimal(initialValue.toString())
    val formatted = NumberFormat.getCurrencyInstance(locale).format(parsed)

    this.setText(formatted)
    this.setSelection(formatted.length)

    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val parsed = parseToBigDecimal(s.toString())
            val formatted = NumberFormat.getCurrencyInstance(locale).format(parsed)

            editText.removeTextChangedListener(this)

            editText.setText(formatted)
            editText.setSelection(formatted.length)

            onInputDouble(parsed.toDouble())

            editText.addTextChangedListener(this)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}

private fun parseToBigDecimal(value: String): BigDecimal {
    val replaceable = String.format("[%s,.\\s]", currencySymbol)

    val cleanString = value.replace(replaceable.toRegex(), "")

    return try {
        BigDecimal(cleanString).setScale(
            2, BigDecimal.ROUND_FLOOR
        ).divide(BigDecimal(100), BigDecimal.ROUND_FLOOR)
    } catch (e: NumberFormatException) {
        //ao apagar todos valores de uma só vez dava erro
        //Com a exception o valor retornado é 0.00
        BigDecimal(0)
    }

}
