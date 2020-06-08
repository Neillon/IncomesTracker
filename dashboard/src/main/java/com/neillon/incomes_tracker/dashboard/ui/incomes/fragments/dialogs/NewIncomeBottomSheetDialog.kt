package com.neillon.incomes_tracker.dashboard.ui.incomes.fragments.dialogs

import android.R.attr.bottom
import android.R.attr.left
import android.R.attr.right
import android.R.attr.top
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.chip.Chip
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.domain.Income
import com.neillon.incomes_tracker.domain.Tag
import kotlinx.android.synthetic.main.fragment_dialog_new_income_bottom_sheet.*
import java.time.LocalDate


class NewIncomeBottomSheetDialog : BottomSheetDialogFragment() {

    private lateinit var bottomSheet: View
    private lateinit var onSave: (income: Income) -> Unit

    @RequiresApi(Build.VERSION_CODES.O)
    private val _income = Income(
        description = "",
        date = LocalDate.now(),
        value = 0.0,
        tags = mutableListOf()
    )

    override fun getTheme(): Int = R.style.BottomSheetDialogStyle

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
        mButtonSaveIncome.setOnClickListener {
            dismiss()
            onSave(_income)
        }

        mChipNewTag.setOnClickListener { chipAddNewTag ->
            val dialog = Dialog(chipAddNewTag.context)
            dialog.setContentView(R.layout.dialog_new_tag_template)

            val mEditTextNewTagDescription =
                dialog.findViewById<EditText>(R.id.mEditTextTagDescription)
            val mButtonSave = dialog.findViewById<MaterialButton>(R.id.mButtonSaveTagDialog)
            val mButtonCancel = dialog.findViewById<MaterialButton>(R.id.mButtonCancelDialog)

            mButtonSave.setOnClickListener {
                val tag = Tag(
                    id = 0L,
                    description = mEditTextNewTagDescription.text.toString(),
                    incomeId = 0L
                )
                _income.tags.add(tag)

                addNewTagChip(chipAddNewTag, tag)
                dialog.dismiss()
            }

            mButtonCancel.setOnClickListener { dialog.dismiss() }
            dialog.show()
        }
    }

    private fun addNewTagChip(addNewTagView: View, tag: Tag) {
        val horizontalGroup = addNewTagView.parent as LinearLayout

        val chip = Chip(addNewTagView.context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.marginStart = 8

        chip.text = tag.description
        chip.closeIcon = getDrawable(addNewTagView.context, R.drawable.ic_baseline_close)
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener { horizontalGroup.removeView(it) }
        chip.layoutParams = params

        horizontalGroup.addView(chip)
    }

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
}