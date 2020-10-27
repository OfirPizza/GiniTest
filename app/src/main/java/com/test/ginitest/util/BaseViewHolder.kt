package com.test.ginitest.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.ginitest.ui.NumberUiModel

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: NumberUiModel)
}