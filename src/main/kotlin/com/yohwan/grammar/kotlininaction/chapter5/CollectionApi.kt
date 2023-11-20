package com.yohwan.grammar.kotlininaction.chapter5

import java.util.*

// filter = 원하지 않는 원소를 제거함, 원소를 변환할 수 없음
// map = 원소를 변환함
// all = 모든 원소가 이 술어를 만족하는지
// any = 술어를 만족하는 원소가 하나라도 있는지
// count = 술어를 만족하는 원소의 개수 구하기
// find = 조건을 만족하는 원소가 하나라도 있는 경우 가장 먼저 조건을 만족한다고 확인된 원소를 반환하며 원소가 없을 경우 null을 반환함, firstOrNull과 같음
fun main(args: Array<String>) {
    val list = listOf(1,2,3,4)
    println(list.filter { it % 2 == 0 }) // 짝수만 남김
    println(list.map { it * it})

    val people = listOf(Person("Alice", 23), Person("Bob", 35))
    println(people.filter { it.age > 30 })
    println(people.map { it.name })
    println(people.map(Person::name))
    println(people.filter { it.age > 30}.map(Person::name))

    println(people.filter { it.age == people.maxBy(Person::age)!!.age}) // 100의 사람이 있따면 100번 최댓값 연산을 수행함
    val maxAge = people.maxBy(Person::age)!!.age
    println(people.filter { it.age == maxAge }) // 아래와 같이 최적화 가능

    val numbers = mapOf(0 to "zero", 1 to "one")
    println(numbers.mapValues { it.value.uppercase(Locale.getDefault()) }) // Map의 경우 filterKeys와 mapKeys로 키를 걸러내거나 변환하고 filterValues와 mapValues로 값을 걸러내거나 변환함

    val canBeInClub27 = { p: Person -> p.age <= 27 }
    println(people.all(canBeInClub27))
    println(people.any(canBeInClub27))
    println(people.count(canBeInClub27))
    println(people.find(canBeInClub27))
    println(people.firstOrNull(canBeInClub27))

    // !를 눈치 못채는 경우가 있으니 이럴 때는 any를 사용하는것이 남
    println(!list.all { it ==3 })
    println(list.any { it != 3 })
}