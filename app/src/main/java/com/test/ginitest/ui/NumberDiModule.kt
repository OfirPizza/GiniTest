package com.test.ginitest.ui

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val numberModule = module {
    factory<NumbersRepo> { NumbersRepoImpl(get()) }
    viewModel { NumbersViewModel(get()) }
}