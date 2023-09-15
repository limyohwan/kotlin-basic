package com.yohwan.grammar.basic

suspend fun main() {
    coroutineFunc(10) {
        println("함수호출")
    }
}

suspend fun coroutineFunc(a:Int, callBack: () -> Unit = {}) {
    println("함수 시작")
    callBack()
    println("함수 끝")
}