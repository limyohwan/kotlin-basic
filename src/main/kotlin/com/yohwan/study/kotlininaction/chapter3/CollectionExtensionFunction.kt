package com.yohwan.study.kotlininaction.chapter3

fun main(args: Array<String>) {
    val strings: List<String> = listOf("first", "second", "fourteenth")
    println(strings.last())

    val numbers: Collection<Int> = setOf(1, 14, 2)
    println(numbers.max())

    // last와 max는 코틀린 표준 라이브러리로 수많은 확장함수를 가지고 있음
    val list = listOf("args: ", *args)// spread 연산자
    println(list)
    // 가변길이 인자 = 자바는 ...을 붙이는 대신 코틀린은 파라미터 앞에 vararg 변경자를 붙임
    // 이미 배열에 들어있는 연산자는 자바에서는 배열을 그냥 넘기면 되지만 코틀린은 배열을 명시적으로 풀어서 넘겨야 함 -> 스프레드 연산자를 사용하며 전달하려는 배열앞에 *를 붙이면 됨

    val map = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    1.to("one") // to 메서드를 일반적인 방식으로 호출
    1 to "one" // to 메서드를 중위 호출 방식으로 호출
    // 중위 호출(infix call)이란 인자가 하나뿐인 일반 메소드나 인자가 하나뿐인 확장 함수에 중위 호출을 사용할 수 있음
    // 중위 호출을 사용하게 허용하려먼 infix 변경자를 함수 선언 앞에 추가해야 함

    val (number, name) = 1 to "one" // 구조 분해 선언(destructuring declaration), Pair 외의 다른 객테에도 구조 분해를 적용할 수있음
    println("$number : $name")
}
