package com.yohwan.grammar.kotlininaction.chapter2

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz"
    i % 3 == 0 -> "Fizz"
    i % 5 == 0 -> "Buzz"
    else -> "$i"
}

fun main(args: Array<String>) {
    playDifficultFizzBuzz()
}

//일반 피즈버즈 게임
fun playFizzBuzz() {
    for (i in 1..100) {
        println(fizzBuzz(i))
    }
}

// 100부터 거꾸로 짝수만 세는 피즈버즈 게임
fun playDifficultFizzBuzz() {
    for (i in 100 downTo 1 step 2) {
        println(fizzBuzz(i))
    }
}