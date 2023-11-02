package com.yohwan.grammar.kotlininaction.chapter3

fun <T> joinToString(
    collection: Collection<T>,
    separator: String =", ",
    prefix: String = "",
    postfix: String = "" // 함수에 디폴트 값을 지정하여 불필요한 오버로딩을 피할 수 있음
) : String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

// 정적인 유틸리티 클래스를 없애고 해당 파일의 최상위 함수로 선언하여 유틸리티 클래스처럼 사용할 수 있음

var opCount = 0
fun performOperation() {
    opCount++
}

fun reportOperationCount() {
    println("Operation performed $opCount times")
}
// 함수와 마찬가지로 프로퍼티도 파일의 최상위 수준에 놓일 수 있음
// 이런 프로퍼티의 값은 정적 필드에 저장됨
// 최상위 프로퍼티를 활용해 코드에 상수를 추가할 수 있음

val UNIX_LINE_SEPARATOR = "\n"
// 최상위 프로퍼티도 다른 모든 프로퍼티처럼 접근자 메소드(getter, setter)를 통해 자바 코드에 노출됨

const val STATIC_UNIX_LINE_SEPARATOR = "\n"
// const를 사용해야 public static final 필드로 컴파일 할 수 있음(원시 타입과 String 타입의 프로퍼티만 const로 지정가능)