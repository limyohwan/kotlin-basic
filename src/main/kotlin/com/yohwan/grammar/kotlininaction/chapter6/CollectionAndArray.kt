package com.yohwan.grammar.kotlininaction.chapter6

import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

fun main(args: Array<String>) {
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)
    addValidNumbers(numbers)
    addValidNumbers2(numbers)
}

fun readNumbers(reader: BufferedReader) : List<Int?> { // List<Int?> = 리스트 안에 원소가 널이 될 수 있음, List<Int>? = 리스트 자체가 널이 될 수 있음, List<Int?>? = 리스트, 원소 둘 다 널이 될 수 있음
    val result = ArrayList<Int?>()

    for (line in reader.lineSequence()) {
        try {
            val number = line.toInt()
            result.add(number)
        } catch (e: NumberFormatException) {
            result.add(null)
        }
    }

    return result
}

fun addValidNumbers(numbers: List<Int?>) {
    var sumOfValidNumbers = 0
    var invalidNumbers = 0
    for (number in numbers) {
        if (number != null) {
            sumOfValidNumbers += number
        } else {
            invalidNumbers++
        }
    }

    println("Sum of valid numbers : $sumOfValidNumbers")
    println("Invalid numbers : $invalidNumbers")
}

fun addValidNumbers2(numbers: List<Int?>) {
    val validNumbers = numbers.filterNotNull() // filterNotNull을 사용해서 위의 복잡한 식을 간단하게 줄일 수 있음
    val sumOfValidNumbers = validNumbers.sum()
    val invalidNumbers = numbers.size - validNumbers.size

    println("Sum of valid numbers : $sumOfValidNumbers")
    println("Invalid numbers : $invalidNumbers")
}