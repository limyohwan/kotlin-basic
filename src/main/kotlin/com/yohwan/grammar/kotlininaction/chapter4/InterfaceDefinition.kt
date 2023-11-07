package com.yohwan.grammar.kotlininaction.chapter4

interface Clickable {
    fun click()
    fun showOff() { // 자바와 달리 메서드 본문을 추가하는 것만으로 default 메서드를 구현할 수 있음
        println("I'm clickable")
    }
}

interface Focusable {
    fun setFocus(b: Boolean) {
        println("I ${if (b) "got" else "lost"} focus")
    }
    fun showOff() {
        println("I'm focusable")
    }
}

class Button : Clickable, Focusable {
    override fun click() { // 자바의 @Override 애노테이션을 사용하는 것 대신 override 키워드를 사용하며 코틀린에서는 꼭 사용해야함 -> 실수로 상위 클래스의 메서드를 오버라이드하는 경우를 방지해주며 상위 클래스와 시그니처가 같은 경우 컴파일이 안됨
        println("I was clicked")
    }

    override fun showOff() { // 상위 인터페이스에 똑같은 메서드가 정의되어 있을 경우(showOff) 코틀린에서는 강제로 해당 메서드를 구현하도록 컴파일러 오류가 발생함
        super<Clickable>.showOff() // super<T>로 상위 타입의 메서드를 호출할 수 있음
        super<Focusable>.showOff()
    }
}

fun main(args: Array<String>) { // 자바에서는 상속은 extends 구현은 implements 키워드를 사용하지만 코틀린에서는 콜론(:)을 붙여 처리함
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}