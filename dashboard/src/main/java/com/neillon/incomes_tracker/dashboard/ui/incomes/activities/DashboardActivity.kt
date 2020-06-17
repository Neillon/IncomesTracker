package com.neillon.incomes_tracker.dashboard.ui.incomes.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.dashboard.adapters.IncomeAdapter
import com.neillon.ioncomes_tracker.presentation.types.IncomeRow
import com.neillon.incomes_tracker.dashboard.databinding.ActivityDashboardBinding
import com.neillon.incomes_tracker.dashboard.ui.extensions.hideBalance
import com.neillon.incomes_tracker.dashboard.ui.extensions.showBalance
import com.neillon.incomes_tracker.dashboard.ui.incomes.fragments.dialogs.income.NewIncomeBottomSheetDialog
import com.neillon.ioncomes_tracker.presentation.viewmodels.IncomeViewModel
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private val incomeViewModel: IncomeViewModel by viewModel { parametersOf(this) }
    private var changeBalance = true
    private val adapter = IncomeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeViews()
        observeViewModel()
    }

    private fun initializeViews() {
        binding.mImageButtonShowBalance.setOnClickListener(::showHideBalance)
        initializeRecyclerViewIncomes()
        mFabNewIncome.setOnClickListener(::openNewIncomeDialog)
    }

    private fun observeViewModel() {
        incomeViewModel.listAll()

        incomeViewModel.balance.observe(this, Observer { mTextViewBalance.text = it })

        incomeViewModel.loadingBalance.observe(this, Observer {
            if (it) mTextViewBalanceLabel.text = getString(R.string.dashboard_balance_label_loading)
            else mTextViewBalanceLabel.text = getString(R.string.dashboard_balance_label)
        })

        incomeViewModel.incomesList.observe(this, Observer { adapter.addData(it) })
    }

    private fun openNewIncomeDialog(view: View) {
        NewIncomeBottomSheetDialog.open(supportFragmentManager) {
            incomeViewModel.saveIncome(it, this@DashboardActivity)
        }
    }

    private fun showHideBalance(view: View) {
        if (changeBalance) {
            binding.mTextViewBalance.hideBalance()
            binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_visibility_off)
        } else {
            binding.mTextViewBalance.showBalance(incomeViewModel.balance.value as String)
            binding.mImageButtonShowBalance.setImageResource(R.drawable.ic_baseline_remove_red_eye)
        }
        changeBalance = !changeBalance
    }

    private fun initializeRecyclerViewIncomes() {
        mRecyclerIncomes.layoutManager = LinearLayoutManager(this@DashboardActivity)
        mRecyclerIncomes.adapter = adapter
    }

}