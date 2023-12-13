package com.yohwan.study.kotlininaction.chapter3

fun main(args: Array<String>) {
    val splitStrings = "12.346-6.a".split(".")
    println(splitStrings)

    val splitStrings2 = "12.345-6.A".split("\\.|-".toRegex()) // 명시적으로 정규식을 지정
    println(splitStrings2)

    val splitStrings3 = "12.345-6.A".split(".", "-") // 여러 구문의 문자열 지정
    println(splitStrings3)

    parsePath("/Users/yohwan/kotlin-book/chapter.adoc")

    parsePath2("/Users/yohwan/kotlin-book/chapter.adoc")

    val kotlinLogo = """| //
                       .|//
                       .|/ \"""
    println(kotlinLogo.trimMargin("."))
}

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    println("Dir: $directory, name: $fileName, ext: $extension") // 코틀린은 정규식을 사용하지 않아도 문자열을 쉽게 파싱할 수 있음
}

fun parsePath2(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex() // 코틀린 라이브러리를 사용하여 더 쉽게 파싱할 수 있음, 삼중따옴표(""") 문자열에서 역슬래시를 포함한 어떤 문자도 이스케이프할 필요가 없음
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, fileName, extension) = matchResult.destructured
        println("Dir: $directory, name: $fileName, ext: $extension")
    }
}