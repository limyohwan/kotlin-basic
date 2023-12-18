package com.yohwan.study.kotlininaction.chapter8

import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.locks.Lock

// inline 함수
// 반복되는 코드를 별도의 라이브러리 함수로 빼내되 컴파일러가 자바의 일반 명령문만큼 효율적인 코드를 생성하는 방법
// inline 변경자를 어떤 함수에 붙이면 컴파일러는 그 함수를 호출하는 모든 문장을 함수 본문에 해당하는 바이트코드로 바꿔치기 해줌
// inline 한수는 inline 함수의 본문에서 람다 식을 바로 호출하거나 람다 식을 인자로 전달받아 호출하는 경우에 그 람다를 인라인 할 수 있으며 아닐경우 컴파일 에러를 발생함
// inline 함수는 람다를 인자로 받는 함수만 성능이 좋아질 가능성이 높음 -> 인라이닝을 통해 함수 호출 비용 감소, 람다 클래스와 람다인스턴스에 해당하는 객체 생성 x, 현재 JVM은 함수 호출과 람다를 인라이닝해줄 정도로 똑똑하지 않음
fun main(args: Array<String>) {
    val people = listOf(Human("Alice", 29), Human("Bob", 31))
    println(people.filter { it.age < 30 })// 람다식을 사용한 거르기

    val result = mutableListOf<Human>()
    for (person in people) { // 람다식을 사용하지 않고 직접 거르기
        if (person.age < 30) result.add(person)
    }
    // 코틀린의 filter는 inline 함수로 람다식을 사용한 방법이 컴파일되면 직접 사용한 방법처럼 동작함 -> 그러므로 성능에 크게 신경쓰지 않아도 됨

    println(people.filter { it.age < 30 }
        .map(Human::name)) // filter와 map은 모두 인라인 함수로 본문에 인라이닝되며 추가 객체나 클래스 생성이 없음 -> 두 함수의 본문이 인라인되며 filter를 위한 중간 리스트가 추가되고 이 리스트를 map이 읽어드리고 또 다른 리스트를 만들기 때문에 처리할 원소가 많아지면 부가 비용이 커짐
    // asSequence를 통해 리스트 대신 시퀀스를 사용하면 중간 리스트로 인한 부가 비용이 줄어듬 -> 각 중간 시퀀스는 람다를 필드에 저장하는 객체로 표현되며 최종 연산에서 중간 시퀀스에 있는 여러 람다를 연쇄 호출함 -> 람다를 인라인하지 않음
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

// 함수를 파라미터로 받은 람다를 다른 변수에 저장하고 나중에 그 변수를 사용한다면 람다를 표현하는 객체가 어딘가는 존재해야 하기 때문에 람다를 인라인 할 수 없음
//fun <T, R> Sequence<T>.map(transform: (T) -> R) : Sequence<R> {
//    return TransformingSequence(this, transform)
//}

// 둘 이상의 람다를 인자로 받는 함수에서 일부 람다만 인라인하는 경우에 noinline 변경자를 파라미터 이름 앞에 붙여 금지할 수 있음
inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
    // ...
}

data class Human(val name: String, val age: Int)

// 코틀린 랄이브러리에 있는 withLock 함수
//fun <T> Lock.withLock(action: () -> T) : T {
//    lock()
//    try {
//        return action()
//    } finally {
//        unlock()
//    }
//}

// 자바의 try-with-resource 문
//static String readFirstLineFromFile(String path) throws IOException {
//    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
//        return br.readLine();
//    }
//}

fun readFirstLineFromFile(path: String) : String {
    BufferedReader(FileReader(path)).use { br -> // BufferedReader 객체를 만들고 use 함수를 호출하면서 파일에 대한 연산을 실행할 람다를 넘김
        return br.readLine() // 파일에서 맨 처음 가져온 한 주을 람다가 아닌 readFirstLineFromFile에서 반환함
    }
    // use 함수는 닫을 수 있는 (closeable) 자원에 대한 확장 함수며 람다를 인자로 받음 -> 람다를 호출한 다음에 자원을 닫아줌, 예외가 발생해도 확실히 닫아줌
}