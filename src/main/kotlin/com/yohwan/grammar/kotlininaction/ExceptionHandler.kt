package com.yohwan.grammar.kotlininaction

import java.io.BufferedReader
import java.io.StringReader
import java.lang.NumberFormatException

fun main(args: Array<String>) {
//    checkPercentage(10000)
    checkPercentage2(10)

    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))
    readNumber2(BufferedReader(StringReader("not number")))
    readNumber3(BufferedReader(StringReader("not number")))
}

fun checkPercentage(percentage: Int) {
    if (percentage !in 0..100) {
        // 자바와 달리 예외를 던질때 new가 필요없음
        throw IllegalArgumentException("percentage must be between 0 ~ 100 : $percentage")
    }
}

// throw는 식이므로 다른 식에 포함될 수 있음
fun checkPercentage2(number: Int) {
    val percentage = if (number in 0..100)
        number
    else
        throw IllegalArgumentException("percentage must be between 0 ~ 100 : $number")

    // 정상 동작 시 percentage에 number가 할당됨
    println(percentage)
}

fun readNumber(reader: BufferedReader) : Int? { // 함수가 던질 예외를 명시하지 않아도 됨
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    }
    catch (e: NumberFormatException) { // : 옆에 예외 타입 작성
        return null
    }
    finally {
        reader.close()
    }
    // 자바에서는 IOException이 체크예외이기때문에 함수 선언 뒤에 thorws IOException이 붙어야 하지만 코틀린에서는 발생한 예외를 잡아도되고 잡지 않아도됨
}

fun readNumber2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return
    }

    println(number)
    // 코틀린의 try 키워드는 if나 when과 마찬가지로 식임
    // try의 값을 변수에 대입할 수 있음
}

fun readNumber3(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }

    println(number)
}