package com.yohwan.study.basic

fun main() {
    myFunc { //별다른 인자가 없을시 () 생략가능
        println("함수 호출")
    }

    myFunc2(10) {
        println("함수 호출")
    }

    myFunc3(10)

    sum {
       a, b -> a + b
    }
}

fun myFunc(callBack: () -> Unit) {
    println("함수 시작")
    callBack()
    println("함수 끝")
}

fun myFunc2(a: Int, callBack: () -> Unit) {// a 인자 추가
    callBack()
}

fun myFunc3(a: Int, callBack: () -> Unit = {}) {// Unit에 기본 지정
    callBack()
}

fun sum(callBack: (a:Int, b:Int) -> Int) {// Unit에 기본 지정
    val result = callBack(10, 20)
    println(result)
}