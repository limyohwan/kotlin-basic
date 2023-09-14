package com.yohwan.grammar.basic

fun main() {
    var items = listOf(1, 2, 3, 4, 5)
    // items.add() 컴파일 에러 listOf로 만든 배열은 불변임

    var mutableItems = mutableListOf(1, 2, 3, 4, 5)
    mutableItems.add(6)
}