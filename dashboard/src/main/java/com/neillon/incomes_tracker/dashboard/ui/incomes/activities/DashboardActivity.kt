package com.neillon.incomes_tracker.dashboard.ui.incomes.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.dashboard.adapters.IncomeAdapter
import com.neillon.incomes_tracker.dashboard.adapters.types.IncomeRow
import com.neillon.incomes_tracker.dashboard.databinding.ActivityDashboardBinding
import com.neillon.incomes_tracker.dashboard.ui.incomes.fragments.dialogs.income.NewIncomeBottomSheetDialog
import kotlinx.android.synthetic.main.activity_dashboard.*

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
        binding.mImageButtonShowBalance.setOnClickListener(::showHideBalance)
        initializeRecyclerViewIncomes()
        mFabNewIncome.setOnClickListener(::openNewIncomeDialog)
    }

    private fun openNewIncomeDialog(view: View) {
        NewIncomeBottomSheetDialog.open(supportFragmentManager) {
            Log.d("DashboardActivity", "Income $it")
        }
    }

    private fun showHideBalance(view: View) {
        if (changeBalance) {
            binding.mTextViewBalance.hideBalance()
            binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_visibility_off)
        } else {
            binding.mTextViewBalance.showBalance(value = "$ 25,480.90")
            binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_remove_red_eye)
        }
        changeBalance = !changeBalance
    }

    private fun initializeRecyclerViewIncomes() {
        incomesList = transformData(generateData())
        val adapter = IncomeAdapter(incomesList)
        mRecyclerIncomes.layoutManager = LinearLayoutManager(this@DashboardActivity)
        mRecyclerIncomes.adapter = adapter
    }

}