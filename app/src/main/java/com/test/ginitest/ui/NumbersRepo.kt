package com.test.ginitest.ui

interface NumbersRepo {
    suspend fun getNumbers(): List<NumberUiModel>
}