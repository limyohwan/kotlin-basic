package com.yohwan.study.kotlininaction.chapter2

import java.util.TreeMap

fun main(args: Array<String>) {
    iterateMap()
    iterateCollection()

    println(isLetter('q'))
    println(isNotDigit('x'))
    println(recognize('8'))

    // 범위는 문자에 국한되지 않고 비교가 가능한 클래스라면 범위를 만들 수 있음
    println("Kotlin" in "Java".."Scala")
    println("Kotlin" in setOf("Java", "Scala"))
}

// 맵에 대한 이터레이션
fun iterateMap() {
    val binaryReps = TreeMap<Char, String>()
    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.code)
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
}

// 컬렉션에 대한 이터레이션
fun iterateCollection() {
    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) {
        println("$index: $element")
    }
}

// in으로 컬렉션이나 범위의 원소 검사
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char) = c !in '0'..'9'

// when에서 in 사용
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "others"
}