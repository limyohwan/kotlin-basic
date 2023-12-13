package com.yohwan.study.kotlininaction.chapter7

import java.math.BigDecimal

// +=, -= 등의 연산자를 복합 대입 연산자라 부름
fun main(args: Array<String>) {
    var point = Point(1, 2)
    point += Point(3, 4) // 객체에 대한 참조를 다른 참조로 바꿈
    println(point)

    val numbers = ArrayList<Int>()
    numbers += 42
    println(numbers[0])

    val list = arrayListOf(1, 2)
    list += 3 // +=는 list를 변경함
    val newList = list + listOf(4,5) // + 는 두 리스트의 모든 원소를 포함하는 새로운 리스트를 반환함
    println(list)
    println(newList)

    val p = Point(10, 20)
    println(-p)

    var bd = BigDecimal.ZERO
    println(bd++) // 후위 증가 연산자는 println이 실행된 다음에 값을 증가함
    println(++bd) // 전위 증가 연산자는 println이 실행되기 전에 값을 증가함
}

operator fun <T> MutableCollection<T>.plusAssign(element: T) { // plusAssign을 정의하면 += 연산자에 이 함수를 사용함, minusAssign, timesAssign 등도 복합 대입 연산자로 사용됨
    this.add(element)
}

operator fun Point.unaryMinus() : Point { // 단항 연산자
    return Point(-x, -y)
}

// +a   unaryPlus
// -a   unaryMinus
// !a   not
// ++a, a++  inc
// --a, a-- dec
operator fun BigDecimal.inc() = this + BigDecimal.ONE