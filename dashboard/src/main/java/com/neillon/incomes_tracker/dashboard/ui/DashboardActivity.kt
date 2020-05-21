package com.neillon.incomes_tracker.dashboard.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.dashboard.adapterType.IncomeRow
import com.neillon.incomes_tracker.dashboard.adapterType.IncomeRowType
import com.neillon.incomes_tracker.dashboard.adapters.IncomeAdapter
import com.neillon.incomes_tracker.dashboard.binding.IncomeBinding
import com.neillon.incomes_tracker.dashboard.databinding.ActivityDashboardBinding
import com.neillon.incomes_tracker.dashboard.ui.extensions.hideBalance
import com.neillon.incomes_tracker.dashboard.ui.extensions.showBalance
import kotlinx.android.synthetic.main.activity_dashboard.*
import java.util.*
import kotlin.collections.ArrayList

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private var changeBalance = true
    private lateinit var incomesList: ArrayList<IncomeRow>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
    }

    private fun initializeViews() {
        binding.mImageButtonShowBalance.setOnClickListener {
            if (changeBalance) {
                binding.mTextViewBalance.hideBalance()
                binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_visibility_off)
            } else {
                binding.mTextViewBalance.showBalance(value = "$ 25,480.90")
                binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_remove_red_eye)
            }
            changeBalance = !changeBalance
        }

        incomesList = transformData(generateData())
        val adapter = IncomeAdapter(incomesList)
        mRecyclerIncomes.layoutManager = LinearLayoutManager(this@DashboardActivity)
        mRecyclerIncomes.adapter = adapter
    }

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

    private fun transformData(incomes: List<IncomeBinding>): ArrayList<IncomeRow> {
        val retorno = arrayListOf<IncomeRow>()
        val groups = incomes.groupBy { item -> item.date }

        for (income in groups) {
            retorno.add(
                IncomeRow(
                    type = IncomeRowType.GROUP,
                    header = income.key,
                    item = null
                )
            )
            income.value.forEach {
                retorno.add(
                    IncomeRow(
                        type = IncomeRowType.ITEM,
                        header = null,
                        item = it
                    )
                )
            }

        }

        return retorno
    }

}