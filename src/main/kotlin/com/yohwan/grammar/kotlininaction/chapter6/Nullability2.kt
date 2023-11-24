package com.yohwan.grammar.kotlininaction.chapter6

import org.springframework.util.Assert

// 6.1.8 ~
fun main(args: Array<String>) {
    verifyUserInput("")
    verifyUserInput(null)

    printHashCode(null) // T의 타입은 Any?로 추론됨
//    printNonNullHashCode(null) // null을 허용하지 않아 컴파일 에러 발생
}

class MyService {
    fun performAction() : String = "foo"
}

class MyTest {
    private var myService: MyService? = null // null로 초기화 하기 위해 null이 될 수 있는 프로퍼티 선업

//    @Before 테스트 예시
    fun setUp() {
        myService = MyService() // 진짜 초기값 지정
    }

//    @Test 테스트 예시
    fun testAction() {
        Assert.hasText("foo", myService!!.performAction()) // 널 가능성을 신경써서 !!나 ?를 사용해야함
    }
}

class MyTest2 {
    private lateinit var myService: MyService // 초기화 하지 않고 널이 될 수 없는 프로퍼티 선언, lateinit 프로퍼티는 항상 var이여야 함, val은 생성자 안에서 무조건 초기화 해야 함

    //    @Before 테스트 예시
    fun setUp() {
        myService = MyService() // 초기값 지정
    }

    //    @Test 테스트 예시
    fun testAction() {
        Assert.hasText("foo", myService.performAction()) // 널 검사를 하지 않아도 프로퍼티 사용 가능
    }
}

fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) { // null인 경우 true를 반환하고, null이 아닌 경우 isBlank를 실행함
        println("Please fill in the required fields")
    }
}

fun <T> printHashCode(t : T) { // 타입 파라미터 T는 기본적으로 null이 될 수 있는 타입임
    println(t?.hashCode()) // t 가 null이 될 수 있는 타입이므로 안전한 호출을 써야함
}

fun <T: Any> printNonNullHashCode(t : T) {
    println(t.hashCode()) // t 가 null이 될 수 없음
}