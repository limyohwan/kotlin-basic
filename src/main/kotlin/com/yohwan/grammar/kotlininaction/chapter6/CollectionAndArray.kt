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

    for(i in args.indices) { // 배열의 인덱스 값의 범위에 대해 이터레이션하기 위해 array.indices 확장 함수를 사용
        println("Argument $i is : ${args[i]}") // array[index]로 인덱스를 사용해 배열 원소에 접근
    }

    // Array 생성
    // arrayOf = 함수에 원소를 넘겨 배열 생성
    // arrayOfNulls = 함수에 정수 값을 인자로 넘기면 모든 원소가 Null이고 인자로 넘긴 값과 크기가 같은 배열을 만들 수 있음
    // Array = 생성자는 배열 크기와 람다를 인자로 받아 람다를 호출해서 각 배열 원소를 초기화 해줌
    val letters = Array<String>(26) { i -> ('a' + i).toString() }
    println(letters.joinToString(""))

    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray())) // vararg 인자를 넘기기 위해 스프레드 연산자(*)를 써야 함

    // Array<Int>는 해당 타입의 박싱된 클래스를 사용함(예: Int -> Integer)
    // 원시 타입을 선언하려면 IntArray -> int[] 를 사용해야 함
    val fiveZeros = IntArray(5)
    val fiveZerosToo = intArrayOf(0, 0, 0, 0, 0)

    val squares = IntArray(5) { i -> (i + 1) * (i + 1) }
    println(squares.joinToString())

    val intList = listOf(1, 2, 3)
    intList.toIntArray() // 박싱된 값이 들어있는 컬렉션이나 배열이 있다면 toIntArray등의 변환 함수를 사용해 박싱하지 않은 값이 있는 배열로 만들 수 있음

    // 코틀린 배열은 컬렉션에서 사용할 수 있는 모든 확장 함수를 제공함
    args.forEachIndexed { index, element ->
        println("Argument $index is: $element")
    }
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