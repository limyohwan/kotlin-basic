package com.yohwan.study.kotlininaction.chapter2

import java.util.*

fun main(args: Array<String>) {
    val rectangle = Rectangle(41, 43)
    println(rectangle.isSquare)
//    println(rectangle.isSquare())
    // 위 두방식을 전부 컴파일하지 못함
    // 두방식의 차이는 가독성임, 클래스의 특성을 정의하고 싶다면 프로퍼티로 그 특성을 정의해야함
}

class Rectangle(
    val height: Int,
    val width: Int
) {
    val isSquare: Boolean
        get() = height == width // 커스텀 게터를 정의하는 방식

//    fun isSquare(): Boolean = height == width // 파라미터가 없는 함수를 정의하는 방식
}

fun createRandomRectangle(): Rectangle {
    val random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}