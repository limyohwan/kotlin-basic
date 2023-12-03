package com.yohwan.grammar.kotlininaction.chapter7

import kotlin.reflect.KProperty

fun main(args: Array<String>) {
    val foo = Foo()
    val oldValue = foo.p
    println(oldValue)
    foo.p = "Updated value"
    println(foo.p)
    // 코틀린 라이브러리는 프로퍼티 위임을 사용해 프로퍼티 초기화를 지연시킬 수 있음
}

class Foo {
    var p: String by Delegate() // by를 사용해 프로퍼티와 위임 객체를 연결함
}

// foo가 컴파일 되었을 때 예시
//class Foo {
//    private val delegate = Delegate() // 컴파일러가 생성한 도우미 프로퍼티
//    var p: String
//    set(value: String) = delegate.setValue(..., value) // p 프로퍼티를 위해 컴파일러가 생성한 접근자는 delegate의 getValue와 setValue 메서드를 호출함
//    get() = delegate.getValue(...)
//}

class Delegate {
    private var storedValue: String = "Initial value" // 초기값 설정

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String { // 게터를 구현하는 로직
        println("Start - Get value: $storedValue")
        return storedValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) { // 세터를 구현하는 로직
        println("Start - Set value: $value")
        storedValue = value
    }
}

