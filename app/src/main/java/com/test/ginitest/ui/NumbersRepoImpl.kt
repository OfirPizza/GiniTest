package com.test.ginitest.ui

import com.test.ginitest.network.NetworkApi
import com.test.ginitest.network.NumberItemResponse
import com.test.ginitest.network.NumberResponse
import com.test.ginitest.util.Result

@Suppress("MoveVariableDeclarationIntoWhen")
class NumbersRepoImpl(private val service: NetworkApi) : NumbersRepo {

    override suspend fun getNumbers(): List<NumberUiModel> {
        val response = Result.build { service.getNumbers() }
        return when (response) {
            is Result.Value -> response.value.toNumberUiModel()
            is Result.Error -> emptyList()
        }
    }
}

private fun NumberResponse.toNumberUiModel(): List<NumberUiModel> {
    val sortNumbers = getSortList(numbers)

    val targetSumNumber = 0
    var startPointer = 0
    var endPointer = sortNumbers.size - 1

    while (startPointer < endPointer) {

        // we have talked on the phone that you want the nearest element to zero
        // when we have the same numbers for example
        // [-125,-125,0,125] -> the index 1 and 3 will be large
        // [-125,-125,0,125,125] -> the index 1 and 3 will be large
        val lastAppearance = sortNumbers.findLast { it.number == sortNumbers[startPointer].number }?:sortNumbers[startPointer]
        val startAppearance = sortNumbers.find { it.number == sortNumbers[endPointer].number }?:  sortNumbers[endPointer]

        val sum = lastAppearance.number + startAppearance.number

        if (sum == targetSumNumber) {
            lastAppearance.haveSumZero = true
            startAppearance.haveSumZero = true
            startPointer++
            endPointer--
            continue
        }

        if (sum < targetSumNumber) {
            startPointer++
            continue
        }
        endPointer--
    }
    return sortNumbers
}

private fun getSortList(numbers: List<NumberItemResponse>) =
    numbers.map { it.number }.sortedBy { it }.map { NumberUiModel(it, false) }