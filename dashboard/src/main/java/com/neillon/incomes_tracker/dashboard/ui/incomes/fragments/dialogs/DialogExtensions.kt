package com.neillon.incomes_tracker.dashboard.ui.incomes.fragments.dialogs

import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import com.google.android.material.chip.Chip
import com.neillon.incomes_tracker.dashboard.R
import com.neillon.incomes_tracker.domain.Tag

infix fun View.createNewChipForTag(tag: Tag) {
    val group = this as LinearLayout

    val chip = Chip(group.context)
    val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    params.marginStart = 8

    chip.text = tag.description
    chip.closeIcon = getDrawable(group.context, R.drawable.ic_baseline_close)
    chip.isCloseIconVisible = true
    chip.setOnCloseIconClickListener { group.removeView(it) }
    chip.layoutParams = params

    group.addView(chip)
}
