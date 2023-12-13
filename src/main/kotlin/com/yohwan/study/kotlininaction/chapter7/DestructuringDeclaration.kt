package com.yohwan.study.kotlininaction.chapter7

fun main(args: Array<String>) {
    // 구조 분해 선언 = 복합적인 값을 분해해서 여러 다른 변수를 한꺼번에 초기화할 수 있음
    // data class의 주 생성자에 들어있는 프로퍼티에 대해서 컴파일러가 자동적으로 componentN 함수를 만들어줌
    val p = Point(10, 20)
    val (x, y) = p
    val a = p.component1() // x
    val b = p.component2() // y
    println("x = $x  a = $a ")
    println("y = $y  b = $b ")

    val (name, ext) = splitFilename("example.kt") // 구조 분해 선언 구문을 사용해 data class를 분해함
    println(name)
    println(ext)

    val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    printEntries(map)
}

data class NameComponents(val name: String, val extension: String) // 값을 저장하기 위한 data class

fun splitFilename(fullName: String) : NameComponents {
    val result = fullName.split('.', limit = 2)
    return NameComponents(result[0], result[1]) // data class 인스턴스 반환
}

fun splitFilename2(fullName: String) : NameComponents {
    val (name, extension) = fullName.split('.', limit = 2) // 코틀린 표준 라이브러리에서는 맨 앞의 다섯 원소에 대한 componenetN을 제공함
    return NameComponents(name, extension) // data class 인스턴스 반환
}

fun printEntries(map: Map<String, String>) {
    for ((key, value) in map) { // 코틀린 표준 라이브러리에는 맵에 대한 확장함수로 iterator가 들어가 있음
        println("$key -> $value")
    }
}

fun printEntries2(map: Map<String, String>) {
    for (entry in map.entries) { // 코틀린 표준 라이브러리에는 맵에 대한 확장함수로 iterator가 들어가 있음
        val key = entry.component1()
        val value = entry.component2()
        println("$key -> $value")
    }
}