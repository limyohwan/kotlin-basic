package com.yohwan.study.kotlininaction.chapter8

import java.util.concurrent.locks.Lock

// inline 함수
// 반복되는 코드를 별도의 라이브러리 함수로 빼내되 컴파일러가 자바의 일반 명령문만큼 효율적인 코드를 생성하는 방법
// inline 변경자를 어떤 함수에 붙이면 컴파일러는 그 함수를 호출하는 모든 문장을 함수 본문에 해당하는 바이트코드로 바꿔치기 해줌
fun main(args: Array<String>) {

}

// 어떤 함수에 inline을 선언한다는 것은 함수를 호출하는 코드를 함수를 호출하는 바이트코드 대신에 함수 본문을 번역한 바이트코드로 컴파일한다는 뜻임
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    }
    finally {
        lock.unlock()
    }
}

fun foo(l: Lock) {
    println("Before sync")
    synchronized(l) {
        println("Action")
    }
    println("After sync")
}
// foo 함수는 이렇게 컴파일됨
fun foo2(l: Lock) {
    println("Before sync")
    l.lock()
    try {
        println("Action")
    }
    finally {
        l.unlock()
    }
    println("After sync")
}

class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        synchronized(lock, body) // 람다 대신 함수 타입 변수를 인자로 넘김
    }
}

class LockOwner2(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) { // 이 함수는 runUnderLock을 실제로 컴파일한 바이트코드와 비슷함
        lock.lock()
        try {
            body() // synchronized를 호출하는 부분에서 람다를 알 수 없으므로 본문(body())는 인라이닝되지 않음
        }
        finally {
            lock.unlock()
        }
    }
}