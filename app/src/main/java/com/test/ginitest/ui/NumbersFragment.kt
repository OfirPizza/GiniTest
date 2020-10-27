package com.test.ginitest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.test.ginitest.R
import kotlinx.android.synthetic.main.fragment_numbers.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class NumbersFragment : Fragment(R.layout.fragment_numbers) {

    private val viewModel: NumbersViewModel by viewModel()
    private val adapter = NumbersAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
    }

    private fun initViewModel() {
        viewModel.apply {
            numbersLiveData.observe(viewLifecycleOwner, { updateAdapter(it) })
            getNumbers()
        }
    }


    private fun initView() {
        recycler_view.adapter = adapter
    }


    private fun updateAdapter(data: List<NumberUiModel>) {
        adapter.updateAdapter(data)
        adapter.notifyDataSetChanged()
    }
}