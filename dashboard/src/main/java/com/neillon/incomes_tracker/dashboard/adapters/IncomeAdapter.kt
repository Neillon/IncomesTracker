package com.neillon.incomes_tracker.dashboard.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.ioncomes_tracker.presentation.types.IncomeRow
import com.neillon.ioncomes_tracker.presentation.types.IncomeRowType
import kotlinx.android.synthetic.main.item_income.view.*
import kotlinx.android.synthetic.main.item_income_group.view.*

abstract class IncomeCustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(income: IncomeRow);
}

class IncomeAdapter() :
    RecyclerView.Adapter<IncomeCustomViewHolder>() {

    private var incomesList = mutableListOf<IncomeRow>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeCustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            IncomeRowType.ITEM.ordinal -> {
                val view = layoutInflater.inflate(R.layout.item_income, parent, false)
                IncomeViewHolder(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.item_income_group, parent, false)
                IncomeHeaderViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = incomesList.size

    override fun getItemViewType(position: Int): Int {
        return incomesList[position].type.ordinal
    }

    override fun onBindViewHolder(holder: IncomeCustomViewHolder, position: Int) {
        val incomeRow = incomesList[position]

        holder.bind(incomeRow)
    }

    fun addData(rows: MutableList<IncomeRow>) {
        incomesList.clear()
        incomesList.addAll(rows)
        notifyDataSetChanged()
    }

    inner class IncomeViewHolder(view: View) : IncomeCustomViewHolder(view) {
        override fun bind(income: IncomeRow) {
            val item = income.item!!

            itemView.mTextViewDescription.text = item.description
            itemView.mTextViewValue.text = "R$ ${item.value.toString().replace('.', ',')}"
        }

    }

    inner class IncomeHeaderViewHolder(view: View) : IncomeCustomViewHolder(view) {
        override fun bind(income: IncomeRow) {
            itemView.mTextViewGroupDate.text = income.groupHeader
        }
    }
}