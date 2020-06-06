package com.neillon.incomes_tracker.dashboard.ui.incomes.fragments.dialogs

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.domain.Income
import kotlinx.android.synthetic.main.fragment_dialog_new_income_bottom_sheet.*
import java.time.LocalDate

class NewIncomeBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var bottomSheet: View
    private lateinit var onSave: (income: Income) -> Unit

    override fun getTheme(): Int = R.style.BottomSheetDialogStyle

    companion object {

        private const val TAG = "BOTTOM_SHEET_NEW_INCOME"

        fun open(
            fragmentManagerInstance: FragmentManager?,
            saveCallback: (income: Income) -> Unit
        ) = NewIncomeBottomSheetDialog().apply {
            fragmentManagerInstance?.let {
                onSave = saveCallback
                show(it, TAG)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_dialog_new_income_bottom_sheet, container, false).apply {
            bottomSheet = this
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        initializeViews()
    }

    override fun onResume() {
        super.onResume()

        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet.parent as View)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initializeViews() {
        mButtonSaveIncome.isEnabled = false
        mButtonSaveIncome.setOnClickListener {
            dismiss()
            onSave(
                Income(
                    description = "salvo",
                    date = LocalDate.now(),
                    value = 20.0,
                    tags = emptyList()
                )
            )
        }
    }

}