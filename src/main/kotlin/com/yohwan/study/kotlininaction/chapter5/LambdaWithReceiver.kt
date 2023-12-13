package com.yohwan.study.kotlininaction.chapter5

// 수신 객체 지정 람다 = 수신 객체를 명시하지 않고 람다의 본문 안에서 다른 객체의 메소드를 호출할 수 있게 하는 것
fun main(args: Array<String>) {
    println(alphabet())
}

fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow I know the alphabet!")
    return result.toString()
}

fun alphabetWith(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) { // 메소드를 호출하려는 수신 객체를 지정
        for (letter in 'A'..'Z') {
            this.append(letter) // this를 명시해서 앞에서 지정한 수신 객체의 메소드를 호출
        }
        append("\nNow I know the alphabet!") // this를 생략하고 메소드를 호출
        this.toString() // 람다에서 값을 반환
    }
}

fun alphabetWith2() = with(StringBuilder()) { // 리팩터링 진행
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        append("\nNow I know the alphabet!")
        this.toString() // 람다의 결과 반환
    }

fun alphabetApply() = StringBuilder().apply { // apply는 자신에게 전달된 객체를 반환함
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet!")
    }.toString()

fun alphabetBuildString() = buildString {// buildString은 StringBuilder를 활용해 String을 만드는 경우 사용하는 해법
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nNow I know the alphabet!")
    }