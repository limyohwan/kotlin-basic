package com.yohwan.grammar.kotlininaction.chapter6

import java.io.BufferedReader
import java.io.File
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

    val list = listOf("a", "b", "c")
    printInUppercase(list)
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
//        읽기 전용           변경 가능
// List = listof    mutableListOf, arrayListOf
// Set =  setOf     mutableSetOf, hashSetOf, linkedSetOf, sortedSetOf
// Map =  mapOf     mutableMapOf, hashMapOf, linkedMapOf, sortedMapOf
fun printInUppercase(list: List<String>) {
    println(CollectionUtils.uppercaseAll(list)) // 자바에서는 컬렉션의 용도가 구별되어 있지 않으므로 코틀린의 컬렉션을 받을 때 이 컬렉션이 읽기 전용인지 변경 가능인지 알 수 없으므로 읽기 전용 컬렉션에 값을 넣는다던지 널이 아닌 컬렉션에 널을 집어넣는 등의 함정이 발생할 수 있음
    println(list.first()) // 변경 불가능에도 불구하고 대문자로 바뀌게 됨
}

class FileIndexer : FileContentProcessor { // 자바 인터페이스를 코틀린에서 구현하기 위해서는 파라미터를 어떠한 형식으로 받을지 결정해야 함
    override fun processContests(path: File, binaryContents: ByteArray?, textContents: List<String>?) {
        TODO("Not yet implemented")
        // 일부 파일은 이진 파일이며 이진 파일 안의 내용은 텍스트로 표현할 수 없는 경우가 있으므로 리스트는 널이 될 수 있음 = List<String>?
        // 파일의 각 줄은 널일 수 없으므로 이 리스트의 원소는 널이 될수 없음 = List<String>
        // 리스트는 파일의 내용을 표현하며 내용을 변경할 수 없으므로 읽기 전용임 = List
    }

}

class PersonParser : DataParser<Person> { // 자바 인터페이스나 클래스가 어떤 맥락에서 사용되는지 정확하게 알아야 코틀린에서 컬렉션에 대한 구현을 어떻게 할지 결정할 수 있음
    override fun parseDate(input: String, output: MutableList<Person>, errors: MutableList<String?>) {
        TODO("Not yet implemented")
        // 호출하는 쪽에서 항상 오류 메시지를 받아야 하므로 errors는 널이 되면 안됨
        // errors의 원소는 널이 될 수 있음, output에 들어가는 정보를 파싱하는 과정에서 오류가 발생하지 않으면 그 정보와 연관된 오류 메시지는 널임
        // 구현 코드에 원소를 추가할 수 있으므로 errors는 변경가능해야 함
    }

}