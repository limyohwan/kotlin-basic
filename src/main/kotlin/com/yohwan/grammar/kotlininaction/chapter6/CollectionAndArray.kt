package com.yohwan.grammar.kotlininaction.chapter6

import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

fun main(args: Array<String>) {
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)
    addValidNumbers(numbers)
    addValidNumbers2(numbers)

    val source: Collection<Int> = arrayListOf(3, 5, 7)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    println(target)
    // 컬렉션 인터페이스의 핵심 = 읽기 전용 컬렉션이라고해서 꼭 변경 불가능한 컬렉션일 필요는 없음

    val testList = arrayListOf(1, 2, 3, 4)
    val testSource: Collection<Int> = testList
    val testTarget: MutableCollection<Int> = testList
    println(testSource)
    testTarget.add(5)
    println(testSource) // 읽기 전용 타입이라고 항상 스레드 세이프하지 않음, 위와 같이 읽기 전용과 변경 가능한 컬렉션 둘다 참조되어 있을 때 그 참조의 값은 바뀔 수 있음
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
    val validNumbers = numbers.filterNotNull() // filterNotNull을 사용해서 위의 복잡한 식을 간단하게 줄일 수 있음, validNumbers의 타입은 List<Int> 타입이 됨
    val sumOfValidNumbers = validNumbers.sum()
    val invalidNumbers = numbers.size - validNumbers.size

    println("Sum of valid numbers : $sumOfValidNumbers")
    println("Invalid numbers : $invalidNumbers")
}

// 읽기 전용, Collection = size, iterator(), contains()
// 변경 가능, MutableCollection = add(), remove(), clear()
fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for (item in source) {
        target.add(item)
    }
}