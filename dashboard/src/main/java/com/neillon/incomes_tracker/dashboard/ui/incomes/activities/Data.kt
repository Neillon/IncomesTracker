package com.neillon.incomes_tracker.dashboard.ui.incomes.activities

import com.neillon.incomes_tracker.dashboard.adapters.types.IncomeRow
import com.neillon.incomes_tracker.dashboard.adapters.types.IncomeRowType
import com.neillon.incomes_tracker.dashboard.binding.IncomeBinding

fun generateData(): ArrayList<IncomeBinding> {
    return arrayListOf(
        IncomeBinding(
            1,
            "Lanche no BK",
            21.99,
            "01/01/2020"
        ),
        IncomeBinding(
            2,
            "Mac Donald's",
            55.0,
            "01/01/2020"
        ),
        IncomeBinding(
            3,
            "ABC",
            13.40,
            "01/02/2020"
        ),
        IncomeBinding(
            4,
            "Varejão do José",
            23.70,
            "01/02/2020"
        ),
        IncomeBinding(
            5,
            "Crédito celular da mãe",
            22.0,
            "01/03/2020"
        ),
        IncomeBinding(
            6,
            "Coca cola",
            8.0,
            "01/03/2020"
        ),
        IncomeBinding(
            7,
            "Refrigerante",
            5.5,
            "01/03/2020"
        ),
        IncomeBinding(
            8,
            "Cinema",
            20.0,
            "01/04/2020"
        ),
        IncomeBinding(
            9,
            "Relógio",
            63.99,
            "01/04/2020"
        )
    )
}

fun transformData(incomes: List<IncomeBinding>): ArrayList<IncomeRow> {
    val retorno = arrayListOf<IncomeRow>()
    val groups = incomes.groupBy { item -> item.date }

    for (income in groups) {
        retorno.add(
            IncomeRow(
                type = IncomeRowType.GROUP,
                groupHeader = income.key,
                item = null
            )
        )
        income.value.forEach {
            retorno.add(
                IncomeRow(
                    type = IncomeRowType.ITEM,
                    groupHeader = null,
                    item = it
                )
            )
        }

    }

    return retorno
}