package com.yohwan.study.kotlininaction.chapter3

fun main(args: Array<String>) {
    println("Kotlin".lastChar)

    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}

val String.lastChar: Char
    get() = get(length -1) // 확장 프로퍼티도 일반적인 프로퍼티와 같은데 단지 수신 객체 클래스가 추가됐을 뿐임
// 뒷바침하는 필드가 없어서 기본 게터구현을 제공할 수 없어서 최소한 게터는 꼭 정의를 해야함
// 초기화 코드에서 계산한 값을 담을 장소가 없어서 초기화 코드도 쓸 수 없음

var StringBuilder.lastChar: Char
    get() = get(length - 1) // 프로퍼티 게터
    set(value: Char) {
        this.setCharAt(length - 1, value) // 프로퍼티 세터
    }

// vararg 키워드를 사용하여 호출 시 인자 개수가 달라질 수 있는 함수를 정의할 수 있음
// 중위 함수 호출 구문을 사용하면 인자가 하나뿐인 메소드를 간편하게 호출할 수 있음
// 구조 분해 선언을 사용하면 복합적인 값을 분해해서 여러 변수에 나눠 담을 수 있음