package com.yohwan.study.kotlininaction.chapter9

import java.lang.Appendable

// 제네릭스 = 타입 파라미터를 받는 타입을 정의할 수 있음
// 제네릭 타입 인스턴스를 만들려먼 타입 파라미터를 구체적인 타입 인자로 치환해야함
fun main(args: Array<String>) {
    val authors = listOf("Dmitry", "Svetlana") // 타입 추론
    val readers:MutableList<String> = mutableListOf() // 빈 리스트는 타입을 직접 명시해야 함
    val readers2 = mutableListOf<String>()

    //fun <T> List<T>.filter(predicate: (T) -> Boolean) : List<T>
//    readers.filter { it !in authors } // it의 타입은 T라는 제네릭 타입임, filter의 수신객체인 reader의 타입이 List<String>이라는 사실을 알고 T가 String이라는 사실을 추론함


    val letters = ('a'..'z').toList()
    println(letters.slice<Char>(0..2))
    println(letters.slice(10..13)) // 컴파일러가 T의 타입을 Char로 추론함

    println(oneHalf(1.5))
    println(max("kotlin", "java"))

    val helloWorld = StringBuilder("Hello World")
    ensureTrailingPeriod(helloWorld)
    println(helloWorld)

    val nullableStringProcessor = Processor<String?>()
    nullableStringProcessor.process(null)

//    val nullableStringProcessor2 = Processor2<String?>() // null이 될 수 없는 타입만 타입파라미터로 지정가능하여 컴파일 에러 발생

}

// <T> -> 타입 파라미터 선언
// 타입 파라미터가 수신 객체(List<T>)와 반환 타입(List<T>)에 쓰임
//fun <T> List<T>.slice(indices: IntRange) : List<T>

interface List<T> { // List 인터페이스에 T라는 타입 파라미터를 정의함
    operator fun get(index: Int): T // 인터페이스 안에서 T를 일반타입처럼 사용가능
}

class StringList: List<String> {
    override fun get(index: Int): String { // 구체적 타입 String을 리턴함
        return this[index]
    }
}

class ArrayList<T>: List<T> {
    override fun get(index: Int): T {
        return this[index]
    }
}

//interface Comparable<T> {
//    fun compareTo(other: T): Int
//}
//
//class String : Comparable<String> { // T의 타입을 String으로 지정
//    override fun compareTo(other: String): Int {
//        return 1
//    }
//}

// Number를 타입파라미터의 상한으로 지정함
fun <T : Number> oneHalf(value: T): Double {
    return value.toDouble() / 2.0 // Number 클래스에 정의된 메소드를 호출
}

fun <T: Comparable<T>> max(first: T, second: T): T { // 함수의 인자들은 비교 가능해야함
    return if (first > second) first else second
}

// 기입 파라미터에 제약을 가하기
fun <T> ensureTrailingPeriod(seq: T) where T : CharSequence, T : Appendable { // 타입 파라미터의 제약 목록
    if (!seq.endsWith('.')) { // CharSequence 인터페이스의 확장함수 호출
        seq.append('.') // Appendable 인터페이스의 메소드 호출
    }
}

class Processor<T> {
    fun process(value: T) {
        value?.hashCode() // null이 될 수 있는 타입 호출 가능
    }
}

class Processor2<T : Any> { // null이 될 수 없는 타입으로 상한 지정
    fun process(value: T) {
        value.hashCode() // null이 될 수 없음
    }
}