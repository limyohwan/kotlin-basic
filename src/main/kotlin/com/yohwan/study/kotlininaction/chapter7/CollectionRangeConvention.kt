package com.yohwan.study.kotlininaction.chapter7

import java.lang.IndexOutOfBoundsException
import java.time.LocalDate

fun main(args: Array<String>) {
    val p = Point(10, 20)
    println(p[1]) // p[1] -> p.get(1)
    // x[a, b] -> x.get(a, b)

    val mp = MutablePoint(10, 20)
    mp[1] = 42 // mp.set(1, 42)
    println(mp)
    // x[a, b] = c -> x.set(a, b, c)

    val rect = Rectangle(Point(10, 20), Point(50, 50))
    println(Point(20, 30) in rect)
    println(Point(5, 5) in rect)

    val now = LocalDate.now()
    val vacation = now..now.plusDays(10) // rangeTo 함수는 LocalDate의 멤버가 아니며 Comparable의 확장 함수임, 다른 산술 연산자보다 우선순위가 낮음
    println(now.plusWeeks(1) in vacation)

    val n = 9
    println(0..(n+1)) // 괄호를 치면 뜻이 명확해짐

    (0..n).forEach { print(it) } // 범위의 메소드를 호출하려면 범위를 괄호로 둘러싸면 됨

    val newYear = LocalDate.ofYearDay(2017, 1)
    val daysOff = newYear.minusDays(1)..newYear
    for (dayOff in daysOff) { println(dayOff) }
}

operator fun Point.get(index: Int) : Int { // x[index] 관례
    return when(index) {
        0 -> x
        1 -> y
        else ->
            throw IndexOutOfBoundsException("invalid coordinate $index")
    }
}

data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.set(index: Int, value: Int) { // x[index] = value 관례
    when(index) {
        0 -> x = value
        1 -> y = value
        else ->
            throw IndexOutOfBoundsException("invalid coordinate $index")
    }
}

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point) : Boolean { // in 관례
    return p.x in upperLeft.x until lowerRight.x && p.y in upperLeft.y until lowerRight.y
}

operator fun ClosedRange<LocalDate>.iterator() : Iterator<LocalDate> =
    object : Iterator<LocalDate> { // LocalDate 의 Iterator 구현
        var current = start

        override fun hasNext(): Boolean { // compareTo 를 사용해 날짜 비교
            return current <= endInclusive
        }

        override fun next(): LocalDate {
            return current.apply {// 현재 날짜를 저장한 다음에 날짜를 변경, 저장해둔 날짜 반환
                current = plusDays(1) // 현재 날짜를 1일 뒤로 변경
            }
        }

    }