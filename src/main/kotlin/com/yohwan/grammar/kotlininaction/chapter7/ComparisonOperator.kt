package com.yohwan.grammar.kotlininaction.chapter7

fun main(args: Array<String>) {
    // a == b -> a?.equals(b) ?: (b == null)
    // kotlin == 은 자바 equals()와 같음(동등성 검사)
    // kotlin === 은 자바 == 과 같음(식별자 비교)

    // a >= b -> a.compareTo(b) >= 0
    // 비교 연산자(<, >, <=, >=)는 compareTo 호출로 컴파일됨
    val p1 = Person("Alice", "Smith")
    val p2 = Person("Bob", "Johnson")
    println(p1 < p2)

    println("abc" < "bac")
}

class Person(
    val firstName: String, val lastName: String
) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }
}