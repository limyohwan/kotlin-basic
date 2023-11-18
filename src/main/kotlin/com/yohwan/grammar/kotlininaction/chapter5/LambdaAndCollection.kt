package com.yohwan.grammar.kotlininaction.chapter5

fun main(args: Array<String>) {
    val people = listOf(Person("Alice", 23), Person("Bob", 28))
    findTheOldest(people)

    println(people.maxBy { it.age })
    println(people.maxBy(Person::age))
}

data class Person(val name: String, val age: Int)

fun findTheOldest(people: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }

    println(theOldest)
}