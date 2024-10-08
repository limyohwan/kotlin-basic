package com.yohwan.study.kotlininaction.chapter11

fun main(args: Array<String>) {
    val s = buildString {
        it.append("Hello, ")
        it.append("World!")
    }
    println(s)

    val s2 = buildString2 {
        this.append("Hello, ")
        append("World!")
    }
    println(s2)

    val appendExcl: StringBuilder.() -> Unit = { this.append("!") }
    val stringBuilder = StringBuilder("Hi")
    stringBuilder.appendExcl()
    println(stringBuilder)
    println(buildString3(appendExcl))

    // with와 apply를 활용하여 코드를 간결하게 만들 수 있음
    val map = mutableMapOf(1 to "one")
    map.apply { this[2] = "two" }
    with (map) { this[3] = "three" }
    println(map)
}

// 일반 람다를 받는 buildString
fun buildString(
    buildAction: (StringBuilder) -> Unit
): String {
    val sb = StringBuilder()
    buildAction(sb)
    return sb.toString()
}

// 수신 객체 지정 람다를 사용해 다시 정의한 buildString()
fun buildString2(
    buildAction: StringBuilder.() -> Unit
): String {
    val sb = StringBuilder()
    sb.buildAction()
    return sb.toString()
}

// buildString2를 축약한 버전
fun buildString3(buildAction: StringBuilder.() -> Unit): String = StringBuilder().apply(buildAction).toString()

/*
* 깔끔한 API란?
* 1. 코드를 읽는 독자들이 어떤 일이 벌어질지 명확하게 이해할 수 있어야 함(이름, 개념 선택 중요)
* 2. 간결한 코드(불필요한 구문 제거, 번잡한 준비 코드 축소)
*
* 코틀린이 지원하는 기능을 이용하여 간결하게 만드는 방법
* 1. 확장 함수: StringUtil.capitalize(s) -> s.capitalize()
* 2. 중위 호출: 1.to("one") -> 1 to "one"
* 3. 연산자 오버로딩: set.add(2) -> set += 2
* 4. get 메소드에 대한 관례: map.get("key") -> map["key"]
* 5. 람다를 괄호 밖으로 빼내는 관례: file.use({ f -> f.read()}) -> file.use { it.read() }
* 6. 수신 객체 지정 람다: sb.append("yes") sb.append("no") -> with(sb) { append("yes") append("no") }
*
* 코틀린 DSL도 온전히 컴파일 시점에 타입이 정해짐
*
* DSL(Domain Specific Language): 압축적인 문법을 사용하여 범옹 언어를 사용하는 경우보다 특정 영역에 대한 연산을 더 간결하게 기술할 수 있음
* 장점: DSL은 선언적(원하는 결과를 기술하기만 하고 그 결과를 달성하기 위해 필요한 세부 실행은 언어를 해석하는 엔진에 맡김)임 -> 실행엔진이 각 연산에 대해 최적화함
* 단점: DSL은 범용 언어로 만든 호스트 애플리케이션과 함께 조합하기가 어려움 -> 이러한 문제를 해결하기 위해 내부 DSL이라는 개념이 나옴
*
* 내부 DSL
* DSL의 핵심 장점을 유지하면서 주 언어를 특별한 방법으로 사용하는 것
*
* DSL의 구조
* API에는 존재하지 않지만 DSL은 구조 또는 문법이라는 특징이 존재함
* API는 메서드를 한번에 하나씩 호출함으로써 라이브러리를 사용함 -> 호출과 다른 호출 사이에 맥락을 알 수 없음(command-query API)
* DSL은 람다를 중첩시키거나 메소드 호출을 연쇄시키는 방식으로 구조를 만듬
* DSL은 같은 문맥을 함수 호출 시마다 반복하지 않고도 재사용할 수 있음, 메소드 호출을 연쇄시킬 수 있음 -> 가독성이 좋아짐
*
* 수신 객체 지정 람다는 구조화된 API를 만들 때 도움이 되는 강력한 코틀린 기능임
* */