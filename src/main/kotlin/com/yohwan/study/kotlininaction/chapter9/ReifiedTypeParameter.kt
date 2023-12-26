package com.yohwan.study.kotlininaction.chapter9

import org.springframework.stereotype.Service
import java.util.ServiceLoader
import kotlin.collections.List

// JVM의 제네릭스는 보통 타입 소거를 사용해 구현됨
// 함수를 inline으로 만들면 타입 인자가 지워지지 않게 수 있음 = 실체화(reify)라고 부름
fun main(args: Array<String>) {
    val list1: List<String> = listOf("a", "b") // String 타입이 소거됨 -> 장점 : 타입 정보는 줄어 메모리 사용량은 줄어듬
    val list2: List<Int> = listOf(1, 2, 3) // Int 타입이 소거됨 -> 단점 : 실행 시점에 타입 인자를 검사할 수 없음 -> if (value is List<String>) {...} 이 불가능함

    printSum(listOf(1, 2, 3))
//    printSum(setOf(1, 2, 3)) // IllegalArgumentException
//    printSum(listOf("a", "b")) // ClassCastException
    printSum2(listOf(1, 2, 3))

    println(isA<String>("abc"))
    println(isA<String>(123))

    // 실체화한 타입 파라미터 사용 예 = filterIsInstance 함수
    val items = listOf("one", 2, "three")
    println(items.filterIsInstance<String>()) // 일치하는 타입 원소만 추려냄

    val serviceImpl = ServiceLoader.load(Service::class.java)
    val serviceImpl2 = loadService<Service>()
}

// 제네릭 타입으로 타입 캐스팅
fun printSum(c: Collection<*>) { // * = 스타 프로젝션 = 타입 파라미터가 2개 이상이라면 모든 타입 파라미터에 *를 포함시켜야함(List<?>와 비슷함)
    val intList = c as? List<Int> ?: throw IllegalArgumentException("List is expefcted")
    println(intList.sum())
}

// 알려진 타입 인자를 사용해 타입 검사하기
fun printSum2(c: Collection<Int>) {
    if (c is List<Int>) {
        println(c.sum())
    }
}

// fun <T> isA(value: Any) = value is T // 타입 인자 정보가 실행시점에 지워져서 확인할 수 없음
inline fun <reified T> isA(value: Any) = value is T // 실체화한 타입 파라미터를 사용하는 함수

// filteredIsInstance 함수 간단 정리
inline fun <reified T> Iterable<*>.filteredIsInstance() : List<T> {
    val destination = mutableListOf<T>()
    for (element in this) {
        if (element is T) {
            destination.add(element)
        }
    }

    return destination
}

inline fun <reified T> loadService(): ServiceLoader<T> {
    return ServiceLoader.load(T::class.java)
}

// 실체화한 타입 파라미터를 사용 가능한 경우
// 타입 검사와 캐스팅(is, !is, as, as?)
// 코틀린 리플렉션 API(::class)
// 코틀린 타입에 대응하는 java.lang.Class를 얻기(::class.java)
// 다른 함수를 호출할 때 타입 인자로 사용

// 실체화한 타입 파라미터 제약
// 타입 파라미터 클래스의 인스턴스 생성
// 타입 파라미터 클래스의 동반 객체 메소드 호출
// 실체화한 타입 파라미터를 요구하는 함수를 호출하면서 실체화하지 않은 타입 파라미터로 받은 타입을 타입 인자로 넘기기
// 클래스, 프로퍼티, 인라인 함수가 아닌 함수의 타입 파라미터를 reified로 지정

// 람다를 인라이닝하고 싶지 않을때 함수 타입 파라미터에 noinline을 붙여 금지할 수 있음