package com.yohwan.study.kotlininaction.chapter6

// 코틀린은 원시타입과 래퍼타입을 구분하지 않으며 같은 타입을 사용함
// 정수 타입 = Byte, Short, Int, Long
// 부동소수점 수 타입 = Float, Double
// 문자 타입 = Char
// 불리언 타입 = Boolean
fun main(args: Array<String>) {
    println(Animal("Sam", 35).isOlderThan(Animal("Amy", 42)))
    println(Animal("Sam", 35).isOlderThan(Animal("Amy")))

    val listOFInts = listOf(1, 2, 3) // JVM은 타입인자로 원시타입을 허용하지 않기 때문에 list는 래퍼클래스인 Integer로 이뤄짐

    val i = 1
//    val l: Long = i // type missmatch 컴파일 오류 발생
    val l: Long = i.toLong() // 직접 변환 메서드를 호출해야 함

    val x = 1
//    x in listOf(1L, 2L, 3L) // 컴파일 오류 발생, 묵시적 타입변환 할 수 없음
    println(x.toLong() in listOf(1L, 2L, 3L)) // 명시적으로 변환해서 값을 비교해야 함

    val a: Byte = 1 // 상수 값은 적절한 타입으로 해석됨
    val b = a + 1L // +는 Byte와 Long을 인자로 받을 수 있음
    foo(42) // 컴파일러에서 자동으로 42를 Long으로 해석함

    println("42".toInt()) // toInt, toByte, toBoolean 과 같은 문자열을 원시타입으로 변환하는 여러 함수를 제공함, 파싱 실패시 NumberFormatException이 발생함
}

data class Animal(val name: String, val age: Int? = null) { // age의 프로퍼티 값은 Integer로 저장됨
    fun isOlderThan(other: Animal) : Boolean? {
        if (age == null || other.age == null) {
            return null
        }

        return age > other.age
    }
}

fun foo(l: Long) = println(l)