package com.yohwan.study.kotlininaction.chapter5

import java.util.*

// filter = 원하지 않는 원소를 제거함, 원소를 변환할 수 없음
// map = 원소를 변환함
// all = 모든 원소가 이 술어를 만족하는지
// any = 술어를 만족하는 원소가 하나라도 있는지
// count = 술어를 만족하는 원소의 개수 구하기
// find = 조건을 만족하는 원소가 하나라도 있는 경우 가장 먼저 조건을 만족한다고 확인된 원소를 반환하며 원소가 없을 경우 null을 반환함, firstOrNull과 같음
// groupBy = 리스트를 여러 그룹으로 이뤄진 맵으로 변경
// flatMap = 인자로 주어진 람다를 컬렉션의 모든 객체에 적용하고(또는 매핑하기, map) 람다를 적용한 결과 얻어지는 여러 리스트를 한 리스트로 한데 모음
// flatten = 리스트의 리스트가 있는데 특별히 변환해야 할 내용이 없다면 flatten을 사용해 평평하게 펼치기만 하면됨
fun main(args: Array<String>) {
    val list = listOf(1,2,3,4)
    println(list.filter { it % 2 == 0 }) // 짝수만 남김
    println(list.map { it * it})

    val people = listOf(Person("Alice", 23), Person("Bob", 35), Person("Carol", 35))
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
    println(people.groupBy { it.age })

    // !를 눈치 못채는 경우가 있으니 이럴 때는 any를 사용하는것이 남
    println(!list.all { it ==3 })
    println(list.any { it != 3 })

    val anyList = listOf("a", "ab", "b")
    println(anyList.groupBy(String::first)) // first는 멤버가 아니라 확장 함수지만 멤버 참조를 사용하여 접근 가능

    val books = listOf(Book("hi", listOf("yohwan", "minji")), Book("bye", listOf("yohwan")), Book("hello", listOf("hani")))
    println(books.flatMap { it.authors }.toSet())

    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })

    val numbersList = listOf(listOf(1,2,3), listOf(5,6,7), listOf(8,9,0))
    println(numbersList.flatten())
}

class Book(val title: String, val authors: List<String>)