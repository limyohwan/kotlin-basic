package com.yohwan.grammar.basic

fun main() {
    var name = "yohwan"

    name.uppercase()
    name.lowercase()
    name[0]

    println("제 이름은 $name 입니다")
    println("제 이름은 ${name}입니다")
    println("제 이름은 ${name + 10}입니다")
}