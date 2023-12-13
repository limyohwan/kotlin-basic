package com.yohwan.study.kotlininaction.chapter4

fun main(args: Array<String>) {
    Example(HiExampleListener()).startExample()

    Example(object : ExampleListener{ // 코틀린의 익명 객체 사용법
        override fun calculate(value: String) {
            println("bye")
        }
    }).startExample()

    val listener = object : ExampleListener { // 변수에도 할당 가능함
        override fun calculate(value: String) {
            println("hello")
        }
    }
    // 객체 선언과 달리 익명 객체는 싱글턴이 아님, 객체 식이 쓰일 때마다 새로운 인스턴스가 생성됨

    introduceMyself()
}

interface ExampleListener {
    fun calculate(value: String)
}

class Example(val exampleListener: ExampleListener) {
    fun startExample() {
        exampleListener.calculate("hi")
    }
}

class HiExampleListener: ExampleListener {
    override fun calculate(value: String) {
        println(value)
    }
}

fun introduceMyself() {
    var age = 1 // 로컬 변수를 정의할 수 있음
    val name = "yohwan"

    Example(object : ExampleListener{
        override fun calculate(value: String) {
            age++ // 로컬 변수의 값을 변경할 수 있음
            println("my name = ${name}, age = ${age}")
        }
    }).startExample()
}