package com.yohwan.study.kotlininaction.chapter2

fun main(args: Array<String>) {
    println("Hello, world!")
    println(max(1,2))
    example()

    val name = "요환"
    println("Hello $name")
    println("${name}님 반가와요") // 문자열 템플릿을 사용할 경우 {}중괄호로 감싸는게 좋음
}

// 함수선언 = fun
// 파라미터 이름 뒤에 파라미터 타입
// 함수를 최상위 수준에 정의할 수 있음, 꼭 클래스 안에 있을 필요 없음
// 배열도 일반적인 클래스, 자바와 달리 배열 처리를 위한 문법 없음
// System.out.println -> println, 코틀린에서는 여러 표준 자바라이브러리 함수를 간결하게 쓸수있도록 제공
// ;(세미콜론)을 붙이지 않아도 됨

fun max(a: Int, b: Int): Int { // 블록이 본문인 함수
    return if(a > b) a else b // Java (a > b) ? a : b
}

// 코틀린 If 는 문장(statement)이 아니고 결과를 만드는 식(expression)이다

fun max2(a: Int, b: Int) = if(a > b) a else b // 식이 본문인 함수

// 타입 추론 = 컴파일러가 타입을 분석하여 프로그램 구성 요소의 타입을 정해줌

fun example() {
    val yearsToCompute = 7.5e6 // double

    val answer: Int // 초기화하지 않고 선언하려면 변수 타입을 반드시 명시해야 함
    answer = 42

    val languages = arrayListOf<String>("Java") // 불변 참조 선언
    languages.add("Kotlin") // 참조가 가리키는 객체 내부 변경

    println(languages)
}

// val = Java의 final 변수
// var = Java의 일반 변수
