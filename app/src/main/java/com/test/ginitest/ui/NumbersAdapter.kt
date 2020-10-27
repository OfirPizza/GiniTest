package com.test.ginitest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.ginitest.R
import com.test.ginitest.util.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_large_number.view.*
import kotlinx.android.synthetic.main.list_item_number.view.*


const val NUMBER = 0
const val LARGE_NUMBER = 1

class NumbersAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var data: List<NumberUiModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return if (viewType == NUMBER) {
            NumberViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_number, parent, false)
            )
        } else {
            LargeNumberViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_large_number, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        return if (data[position].haveSumZero) {
            LARGE_NUMBER
        } else {
            NUMBER
        }
    }

    fun updateAdapter(newData: List<NumberUiModel>) {
        data = newData
    }


    class LargeNumberViewHolder(view: View) : BaseViewHolder(view) {

        override fun bind(item: NumberUiModel) {
            itemView.large_number_tv.text = item.number.toString()
        }

    }

    class NumberViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(item: NumberUiModel) {
            itemView.number_tv.text = item.number.toString()
        }
    }
}