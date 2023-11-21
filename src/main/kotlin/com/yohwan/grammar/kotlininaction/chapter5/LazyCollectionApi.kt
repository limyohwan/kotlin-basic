package com.yohwan.grammar.kotlininaction.chapter5

import java.io.File

// map과 filter 같은 몇가지 컬렉션 함수는 결과 컬렉션을 즉시(eagerly) 생성함
// 시퀀스(sequence)를 사용하면 중간 임시 컬렉션을 사용하지 않고도 컬렉션 연산을 연쇄할 수 있음
fun main(args: Array<String>) {
    val people = listOf(Person("Alice", 23), Person("Bob", 35), Person("Carol", 35))

    people.map(Person::name).filter { it.startsWith("A") } // 이 연쇄 호출은 리스트를 2개 만든다는 뜻임, 원소가 많아지면 많아질 수록 성능이 저하됨
    people.asSequence().map(Person::name).filter { it.startsWith("A") }.toList() // 각 연산을 효율적으로 하기위해 컬렉션을 직접 사용하는 대신 시퀀스를 사용하게 만들어야됨
    // 코틀린의 지연 계산 시퀀스는 Sequence 인터페이스에서 시작함
    // Sequence 인터페이스의 강점은 인터페이스 위에 구현된 연산이 계산을 수행하는 방법 떄문에 생김, 시퀀스의 원스는 필요할 때 비로소 계산됨 -> 중간 처리 결과를 저장하지 않고도 연산을 연쇄적으로 적용해서 효율적으로 계산을 수행할 수 있음
    // asSequence()를 활용해서 어떤 컬렉션도 sequence로 만들 수 있음, 중간 연산 = map, filter, 최종 연산 = toList()

    // 최종 연산이 없는 예제
    listOf(1, 2, 3, 4).asSequence().map { print("map($it) "); it * it }.filter { print("filter($it) "); it % 2 == 0 } // 아무 내용도 출력되지 않음, 최종 연산이 호출될 때 중간 연산을 시작함

    listOf(1, 2, 3, 4).asSequence().map { print("map($it) "); it * it }.filter { print("filter($it) "); it % 2 == 0 }.toList() // 시퀀스의 경우 모든 연산이 순차적으로 적용됨

    println(listOf(1, 2, 3, 4).asSequence().map { it * it }.find { it > 3}) // 즉시 계산은 전체 컬렉션에 연산을 적용하지만 지연 계산은 원소를 한번에 하나씩 처리함
    // 즉시 계산은 it * it한 컬렉션(1, 4, 9 ,16)으로 변환한뒤  3초과인 것을 찾는 반면 지연 계산은 1 넣고 3보다 큰지 확인하고 4넣고 3보다 큰지 확인하고 이런식으로 값을 찾아감

    println(people.asSequence().map(Person::name).filter { it.length < 4 }.toList())
    println(people.asSequence().filter { it.name.length < 4 }.map(Person::name).toList()) // filter를 먼저 적용하면 전체 변환 횟수가 줄어듬

    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum()) // naturalNumbers와 numbersTo100 모두 시퀀스며 최종연산인 sum()이 수행되기 전까지 각 숫자는 계산되지 않음

    val file = File("/User/yohwan/.HiddenDir/a.txt")
    println(file.isInsideHiddenDirectory())
}

fun File.isInsideHiddenDirectory() =
    generateSequence(this) { it.parentFile }.any { it.isHidden } // 시퀀스를 활용하면 조건을 만족하는 디렉터리를 찾은 뒤 더 이상 상위 디렉터리를 뒤지지 않음