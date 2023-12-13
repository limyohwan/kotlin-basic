package com.yohwan.study.kotlininaction.chapter2

fun main(args: Array<String>) {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr) : Int {
    if (e is Num) {
        val n = e as Num // 불필요한 타입 변환
        return n.value
    }

    if (e is Sum) {
        return eval(e.right) + eval(e.left) // 변수 e에 대해 스마트 캐스트를 사용, 인텔리제이가 배경색으로 스마트 캐스팅한 부분을 표시해줌
    }

    throw IllegalArgumentException("Unknown expression")
}
// 스마트 캐스트 (smart cast)
// 코틀린에서는 프로그래머 대신 컴파일러가 캐스팅 해줌
// 어떤 변수가 원하는 타입인지 일단 is로 검사하고 나면 굳이 변수를 원하는 타입으로 캐스팅하지 않아도 마치 처음 부터 그 변수가 원하는 타입으로 선언된 것처럼 사용할 수 있음
// 실제로는 컴파일러가 캐스팅을 수행해줌
// 스마트 캐스트를 사용하기 위해서는 값이 변할 수 없는 val일 경우에만 가능함

fun eval2(e: Expr) : Int =
    if (e is Num) {
        e.value
    } else if (e is Sum) {
        eval2(e.right) + eval2(e.left) // 변수 e에 대해 스마트 캐스트를 사용, 인텔리제이가 배경색으로 스마트 캐스팅한 부분을 표시해줌
    } else {
        throw IllegalArgumentException("Unknown expression")
    }
// if문 분기로 수정

fun eval3(e: Expr) : Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval3(e.right) + eval3(e.left) // 변수 e에 대해 스마트 캐스트를 사용, 인텔리제이가 배경색으로 스마트 캐스팅한 부분을 표시해줌
        else -> throw IllegalArgumentException("Unknown expression")
    }
// when 으로 수정

fun evalWithLogging(e: Expr) : Int =
    when (e) {
        is Num -> {
            println("num : ${e.value}")
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum : $left + $right")
            left + right
        }
        else -> throw IllegalArgumentException("Unknown expression")
    }
// 복잡한 분기가 들어있는 when 사용