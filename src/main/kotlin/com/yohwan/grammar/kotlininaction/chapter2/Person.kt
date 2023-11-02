package com.yohwan.grammar.kotlininaction.chapter2

data class Person(
    val name: String,
    val age: Int? = null
)

fun main(args: Array<String>) {
    val persons = listOf(Person("영희"), Person("철수", age = 29))
    val oldest = persons.maxBy { it.age ?: 0 }
    println("oldest person : $oldest")
}
