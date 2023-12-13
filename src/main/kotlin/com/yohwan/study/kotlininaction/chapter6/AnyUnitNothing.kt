package com.yohwan.study.kotlininaction.chapter6

import java.lang.IllegalStateException

// 자바에서 Object가 최상위 타입이듯 코틀린에서는 Any 타입이 모든 널이 되룻 없는 타입의 조상 타입임
// 자바에서는 참조 타입만 Object를 정점으로 하는 타입 계층에 포함되며 원시타입은 들어있지 않지만 코틀린의 Any는 Int 등의 원시 타입을 포함한 모든 타입의 조상 타입임
// 자바와 마찬가지로 코틀린에서도 원시 타입 값을 Any 타입으 ㅣ변수에 대입하면 자동으로 값을 객체로 감쌈
fun main(args: Array<String>) {
    val answer: Any = 42 // Any는 널이 될 수 없는 타입임, 널을 허용할려면 Any?를 사용해야 함

//    fail("error occured")

    val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val jetbrainsAddress = jetbrains.address ?: fail("No address") // Nothing은 엘비스 연산자의 우항에 사용해서 전제 조건을 검사할 수 있음
    println(jetbrainsAddress.city)
}

// Unit은 자바의 void와 같은 역할을 함
fun f() : Unit {}

fun f2() {} // 반환타입을 명시하지 않으면 위와 같음

// void 와 Unit의 차이점 = Unit은 모든 기능을 갖는 일반적인 타입이며 void와 달리 Unit을 타입 인자로 쓸 수 있음
interface Processor<T> {
    fun process(): T
}

class NoResultProcessor : Processor<Unit> {
    override fun process() {
        // Unit타입이기 떄문에 return을 명시할 필요가 없음
//        return Unit // 컴파일러가 묵시적으로 이 코드를 넣어줌
        // java.lang.Void 타입을 사용하는 방법도 있는데 Void 타입에 대응하는 값인 null을 반환하기 위해 return null을 명시해야 함
    }
}

fun fail(message: String) : Nothing { // Nothing 타입은 아무 값도 포함하지 않음, 함수의 반환 타입이나 반환 타입으로 쓰일 타입 파라미터로만 쓸 수 있음
    throw IllegalStateException(message)
}