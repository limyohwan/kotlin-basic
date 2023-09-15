package com.yohwan.grammar.basic

fun main() {

}

interface Runnable {
    fun run()
}

class Tiger : Animal(), Runnable {
    override fun run() {
        TODO("Not yet implemented")
    }
}

class Lion : Animal(), Runnable {
    override fun run() {
        TODO("Not yet implemented")
    }
}