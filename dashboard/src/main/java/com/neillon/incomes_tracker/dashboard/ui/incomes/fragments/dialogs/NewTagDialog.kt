package com.neillon.incomes_tracker.dashboard.ui.incomes.fragments.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.widget.Button
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.domain.Tag

class NewTagDialog(context: Context) : Dialog(context) {

    private val tag: Tag = Tag(
        id = 0L,
        description = "",
        incomeId = 0L
    )

    private lateinit var mEditTextNewTagDescription: EditText
    private lateinit var mButtonSave: Button
    private lateinit var mButtonCancel: Button

    fun newInstance(): NewTagDialog {
        setContentView(R.layout.dialog_new_tag_template)

        mEditTextNewTagDescription = findViewById(R.id.mEditTextTagDescription)
        mButtonSave = findViewById(R.id.mButtonSaveTagDialog)
        mButtonCancel = findViewById(R.id.mButtonCancelDialog)
        return this
    }

    fun onSave(onSave: (dialog: NewTagDialog, tag: Tag) -> Unit): NewTagDialog {
        mButtonSave.setOnClickListener {
            tag.description = mEditTextNewTagDescription.text.toString()
            onSave(this, tag)
        }
        return this
    }

    fun onCancel(onCancel: (dialog: NewTagDialog) -> Unit): NewTagDialog {
        mButtonCancel.setOnClickListener { onCancel(this) }
        return this
    }

    fun openDialog() = show()
}