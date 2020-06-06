package com.neillon.incomes_tracker.dashboard.ui.incomes.fragments.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.neillon.incomes_tracker.dashboard.R

class NewIncomeBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var bottomSheet: View

    override fun getTheme(): Int = R.style.BottomSheetDialogStyle

    companion object {

        private const val TAG = "BOTTOM_SHEET_NEW_INCOME"

        fun open(fragmentManagerInstance: FragmentManager?) = NewIncomeBottomSheetDialog().apply {
            fragmentManagerInstance?.let { show(it, TAG) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bottomSheet = inflater.inflate(
            R.layout.fragment_dialog_new_income_bottom_sheet,
            container,
            false
        )

        return bottomSheet
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun onResume() {
        super.onResume()

        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet.parent as View)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }
}