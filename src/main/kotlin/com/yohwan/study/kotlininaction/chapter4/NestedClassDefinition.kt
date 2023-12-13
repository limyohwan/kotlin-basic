package com.yohwan.study.kotlininaction.chapter4

import java.io.Serializable

interface State: Serializable

interface View {
    fun getCurrentState() : State
    fun restoreState(state: State) {}
}

// 자바는 다른 클래스 안에 정의한 클래스는 자동으로 내부 클래스(inner class)가 됨
// 자바는 중첩 클래스를 static으로 선언하여 그 클래스를 둘러싼 바깥쪽 클래스에 대한 묵시적인 참조가 사라짐

class NewButton : View {
    override fun getCurrentState(): State = ButtonState()

    class ButtonState : State {} // 자바의 정적 중첩 클래스와 대응함, 코틀린 중첩 클래스에 아무런 변경자가 붙지 않으면 자바 static 중첩 클래스와 같음
    // 이를 내부 클래스로 변경해서 바깥쪽 클래스에 대한 참조를 포함하게 만들고 싶다면 inner 변경자를 붙여야 함
}

// 클래스 B 안에 정의된 클래스 A                        자바                코틀린
// 중첩 클래스(바깥쪽 클래스에 대한 참조를 저장하지 않음)   static class A        class A
// 내부 클래스(바깥쪽 클래스에 대한 참조를 저장함)          class A              inner class A

class Outer {
    inner class Inner {
        fun getOuterReference() : Outer = this@Outer // 내부 클래스 Inner에서 바깥쪽 클래스 Outer 참조에 접근하려면 this@를 붙여야 함
    }
}


interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr) : Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("errorrrr") // 코틀린 컴파일러는 when을 사용해 Expr 타입을 검사할 때 꼭 디폴트 분기인 else를 강제함, 만약 Multiple이라는 새로운 Expr이 추가되면 모두 else로 빠지게 됨
    }

sealed class Expr2 { // sealed로 표시된 클래스는 자동으로 open 임
    class Num2(val value: Int) : Expr2()
    class Sum2(val left: Expr2, val right: Expr2) : Expr2()
    class Multiple() : Expr2() // Multiple 추가되면 자동으로 밑의 when 문에 컴파일 에러가 발생함
}

//fun eval(e: Expr2) : Int =
//    when (e) {
//        is Expr2.Num2 -> e.value
//        is Expr2.Sum2 -> eval(e.right) + eval(e.left) // when 식에서 sealed 클래스의 모든 하위 클래스를 처리한다면 else 분기가 필요가 없음
//    }