package com.yohwan.grammar.basic

import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.random.Random

fun main() {
    var i = 10
    var j = 20
    println(max(i, j))
    println(min(i, j))

    val random = Random.nextInt()
    println(random)

    val randomRange = Random.nextInt(1, 101) // 1 ~ 100
    println(randomRange)
}