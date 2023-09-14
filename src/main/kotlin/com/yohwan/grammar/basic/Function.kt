package com.yohwan.grammar.basic

fun main() {
    println(sum(1, 2))
    println(sum4(a = 1, b = 2))
    println(sum4(b = 2, a = 1)) // 입력파라미터를 명시적으로 대입 가능
}

fun sum(a: Int, b: Int) : Int {
    return a + b
}

fun sum2(a: Int, b: Int) : Int = a + b

fun sum3(a: Int, b: Int) = a + b

fun sum4(a: Int, b: Int, c: Int = 0) = a + b + c // default 값 부여 가능