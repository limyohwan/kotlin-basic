package com.yohwan.study.kotlininaction.chapter3

fun main(args: Array<String>) {
    Button().click()
    val button: View = Button()
    button.showOff() // 확장 함수는 정적으로 결정됨 -> view가 호출됨
    // 확장함수의 첫번째 인자가 수신 객체인 정적 자바 메소드로 컴파일함
    // 코틀린은 호출될 확장 함수를 정적으로 결정하기 떄문에 오버라이드할 수 없음
}

open class View {
    open fun click() = println("view clicked")
}

class Button: View() {
    override fun click() = println("button clicked")
}

fun View.showOff() = println("im a view")

fun Button.showOff() = println("im a button")