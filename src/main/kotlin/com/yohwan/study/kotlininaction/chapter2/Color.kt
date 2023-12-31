package com.yohwan.study.kotlininaction.chapter2

import com.yohwan.study.kotlininaction.chapter3.lastChar
// 확장함수는 임포트 해야하며 한 클래스에 같은 이름의 확장함수가 둘 이싱있어서 이름이 충돌하는 경우가 자주 생길 수 있음
// import com.yohwan.study.kotlininaction.chapter3.*
// *도 사용 가능
// import com.yohwan.study.kotlininaction.chapter3.lastChar as last
// as를 통해 다른 이름으로 부를 수도 있음

fun main(args: Array<String>) {
    println(Color.BLUE.rgb())
    println(getMnemonic(Color.BLUE))
    println("임요환".lastChar())
//    println("임요환".last())
}

enum class Color(
    val r: Int,
    val g: Int,
    val b: Int
) {
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255); // enum은 ; 이 필요함

    fun rgb() = (r * 256 + g) * 256 + b
}

// 자바 enum, 코틀린 enum class -> 코틀린의 선언이 긴 몇 안되는 케이스

fun getMnemonic(color: Color) =
    when (color) {
        Color.RED -> "Richard1"
        Color.ORANGE -> "Richard2"
        Color.YELLOW -> "Richard3"
        Color.GREEN -> "Richard4"
        Color.BLUE -> "Richard5"
    }

fun getWarmth(color: Color) =
    when (color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
        Color.GREEN -> "neutral"
        Color.BLUE -> "cold"
    }

// when은 자바의 switch에 해당하는 코틀린 구성요소임
// 분기 끝마다 break를 넣지 않아도됨
// 한분기에 ,를 통해 여러값을 넣을 수 있음
// enum 클래스로 선언되어 있을시 해당 enum에 선택하는 값이 매핑되지 않으면 else를 선언해주던가 모든 값을 매핑해주어야 컴파일에러가 발생하지 않음
// enum은 자바의 static import처럼 사용가능함

fun mix(c1: Color, c2: Color) =
    when(setOf(c1, c2)) {
        setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
        setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
        else -> throw Exception("dirty color")
    }

// 위의 setOf를 자주 호출하게 되면 불필요한 가비지 객체가 늘어나게됨
// when은 아무 인자도 없이 사용할 수 있으며 각 분기의 조건이 불리언 결과를 계산하는 식이어야 함 -> 가독성은 떨어짐
fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == Color.RED && c2 == Color.YELLOW) || (c1 == Color.YELLOW && c2 == Color.RED) -> Color.ORANGE
        (c1 == Color.YELLOW && c2 == Color.BLUE) || (c1 == Color.BLUE && c2 == Color.YELLOW) -> Color.GREEN
        else -> throw Exception("dirty color")
    }

// 상수(enum 상수, 숫자 리터럴)만 사용가능했던 자바와는 달리 코틀린에서는 임의의 객체를 허용함