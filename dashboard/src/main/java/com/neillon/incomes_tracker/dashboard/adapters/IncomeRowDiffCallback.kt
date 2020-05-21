package com.neillon.incomes_tracker.dashboard.adapters

import androidx.recyclerview.widget.DiffUtil
import com.neillon.incomes_tracker.dashboard.adapterType.IncomeRow

class IncomeRowDiffCallback(private val newRows: List<IncomeRow>, private val oldRows: List<IncomeRow>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRow = oldRows[oldItemPosition]
        val newRow = newRows[newItemPosition]

        return oldRow.type == newRow.type
    }

    override fun getOldListSize(): Int = oldRows.size

    override fun getNewListSize(): Int = newRows.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRow = oldRows[oldItemPosition]
        val newRow = newRows[newItemPosition]

        return oldRow == newRow
    }
}
