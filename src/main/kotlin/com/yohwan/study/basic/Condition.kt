package com.yohwan.study.basic

fun main() {
    var i = 5

    // if 문
    if(i > 10) {
        println("bigger than 10")
    } else if(i > 5) {
        println("bigger than 5")
    } else {
        println("smaller than 5")
    }

    // when 문
    when {
        i > 10 -> {
            println("bigger than 10")
        }
        i > 5 -> {
            println("bigger than 5")
        }
        else -> {
            println("smaller than 5")
        }
    }

    // if문 리턴 값을 바로 받을 수 있음
    var result = if(i > 10) {
        "bigger than 10"
    } else if(i > 5) {
        "bigger than 5"
    } else {
        "smaller than 5"
    }
    println(result)

    // when 리턴 값을 바로 받을 수 있음
    var result2 = when {
        i > 10 -> {
            "bigger than 10"
        }
        i > 5 -> {
            "bigger than 5"
        }
        else -> {
            "smaller than 5"
        }
    }
    println(result2)
}