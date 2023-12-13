package com.yohwan.study.basic

fun main() {
    var name: String? = null // ?를 사용해야 null을 넣을 수 있음, 코틀린은 기본적으로 null safety임
    name = "요환"
    name = null

    var name2: String = ""
    // name2 = name String 과 String? 의 타입은 다른것임
    if(name != null) { // 그래서 null 체크를 해야지만 비교가 가능함
        name2 = name
    }

    name2 = name!! // 또는 !!두개를 사용하여 null이 아님을 보장해줄 수 있음 -> 에러가 발생할 수 있음

    name?.let {// null이 아니라면
        name2 = name
    }
}