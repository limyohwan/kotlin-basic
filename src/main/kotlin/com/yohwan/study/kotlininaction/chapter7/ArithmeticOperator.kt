package com.yohwan.study.kotlininaction.chapter7

// 오버로딩 가능한 이항 산술 연산자(관례)
// a * b  times
// a / b  div
// a % b  mod(1.1부터 rem)
// a + b  plus
// a - b  minus
// 연산자의 우선순위는 언제나 표준 숫자 타입에 대한 우선순위와 같음
fun main(args: Array<String>) {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2) // +로 계산하면 plus 함수가 호출됨, p1.plus(p2)
    println(p1.plus(p2))

    println(p1 * 1.5)
    println(1.5 * p1)

    println('a' * 3)
    println('a' * 3.0)

    // 비트 연산자는 특별한 연산자 함수를 사용하지 않음
    // 커스텀 타입에서 비트 연산자를 정의할 수 없음
    // shl - 왼쪽 시프트(<<)
    // shr - 오른쪽 시프트(부호 비트 유지, >>)
    // ushr - 오른쪽 시프트(0으로 부호 비트 설정, 자바 >>>)
    // and - 비트 곱(&)
    // or - 비트 합(|)
    // xor - 비트 배타(^)
    // inv - 비트 반전(~)
    println(0x0F and 0xF0)
    println(0x0F or 0xF0)
    println(0x1 shl 4)
}

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) : Point { // 연산자를 오버로딩하는 함수 앞에는 꼭 operator가 붙어야함
        return Point(x + other.x, y + other.y)
    }
}

operator fun Point.plus(other: Point) : Point { // 확장함수로 정의 가능
    return Point(x + other.x, y + other.y)
}

operator fun Point.times(scale: Double) : Point { // 연산자를 정의할 때 두 피연산자가 같은 타입일 필요가 없음
    return Point((x * scale).toInt(), (y * scale).toInt())
}

operator fun Double.times(p: Point) : Point { // p * 1.5 라고 해서 1.5 * p 가 적용되지 않으므로 같은 식에 대응하는 연산자 함수를 만들어 주어야 함
    return Point((p.x * this).toInt(), (p.y * this).toInt())
}

operator fun Char.times(count: Int) : String { // 결과 타입이 피연산자와 달라도 가능함
    return toString().repeat(count)
}

operator fun Char.times(count: Double) : String { // 일반 함수 처럼 오버로딩 가능하며 이름은 같지만 파라미터 타입이 다른 여러가지 함수를 만들 수 있음
    return toString().repeat(count.toInt()).plus("double")
}