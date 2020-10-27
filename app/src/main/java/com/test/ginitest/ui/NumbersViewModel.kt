package com.test.ginitest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class NumbersViewModel(private val numbersRepo: NumbersRepo) : ViewModel() {

    private val numbersMutableLiveData = MutableLiveData<List<NumberUiModel>>()
    val numbersLiveData: LiveData<List<NumberUiModel>> = numbersMutableLiveData

    fun getNumbers() {
        CoroutineScope(IO).launch {
            postNumbers(numbersRepo.getNumbers())
        }
    }

    private fun postNumbers(numbers: List<NumberUiModel>) {
        numbersMutableLiveData.postValue(numbers)
    }
}