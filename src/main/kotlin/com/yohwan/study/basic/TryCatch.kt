package com.yohwan.study.basic

import java.lang.Exception

fun main() {
    val items = arrayOf(1, 2, 3, 4, 5)

    try {
        val item = items[5]
    } catch (e: Exception) {
        println(e.message)
    }
}