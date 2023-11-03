package com.yohwan.grammar.kotlininaction.chapter3

fun main(args: Array<String>) {
    val createdHashSet = createHashSet()
    println(createdHashSet.max())

    val createdArrayList = createArrayList()
    println(createdArrayList.last())
    println(createdArrayList) // 기본적으로 toString 구현이 들어있어 원소를 쉽게 찍어볼 수 있음
    println(joinToString(createdArrayList, ";", "(", ")"))
    println(joinToString(createdArrayList, separator = ";", prefix = "(", postfix = ")")) // 코틀린은 함수를 호출할때 이름을 명시하여 혼동을 막을 수 있음


    val createdHashMap = createHashMap()
    createdHashMap.forEach { (t, u) ->
        println("$t : $u")
    }

    val list = listOf(1, 2, 3)
    println(list.joinToString2(separator = ";", prefix = "(", postfix = ")")) // 컬렉션 확장함수를 사용한 예시
    // 확장함수는 단지 정적 메소드 호출에 대한 문법적인 편의(syntatic sugar)일 뿐임

    println(listOf("one", "two", "three").join("||"))
}

fun createHashSet(): HashSet<Int> {
    return hashSetOf(1, 7, 53) // java.util.HashSet
}

fun createArrayList(): ArrayList<Int> {
    return arrayListOf(1, 7, 53) // java.util.ArrayList
}

fun createHashMap(): HashMap<Int, String> {
    return hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three") // java.util.HashMap
}
// 코틀린이 자신만의 컬렉션 기능을 제공하지 않는 이유는 표준 자바 컬렉션을 활용하면 자바 코드와 상호작용하기 쉽기 때문임

//fun <T> joinToString(
//    collection: Collection<T>,
//    separator: String =", ",
//    prefix: String = "",
//    postfix: String = "" // 함수에 디폴트 값을 지정하여 불필요한 오버로딩을 피할 수 있음
//) : String {
//    val result = StringBuilder(prefix)
//
//    for ((index, element) in collection.withIndex()) {
//        if (index > 0) result.append(separator)
//        result.append(element)
//    }
//
//    result.append(postfix)
//    return result.toString()
//}