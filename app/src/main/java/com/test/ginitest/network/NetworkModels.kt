package com.test.ginitest.network

data class NumberResponse(
    val numbers: List<NumberItemResponse>
)

data class NumberItemResponse(
    val number: Int
)