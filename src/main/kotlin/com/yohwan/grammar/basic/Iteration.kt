package com.yohwan.grammar.basic

fun main() {
    var items = listOf(1, 2, 3, 4, 5)

    for(item in items) {
        println(item)
    }

    items.forEach { item ->
        println(item)
    }

    for(i in 0 .. (items.size - 1)) {
        println(items[i])
    }

    for(i in 0 until items.size) {
        println(items[i])
    }

    for((index, item) in items.withIndex()) {
        println(item)
    }
}