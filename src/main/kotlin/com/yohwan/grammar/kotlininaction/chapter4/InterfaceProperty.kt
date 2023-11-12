package com.yohwan.grammar.kotlininaction.chapter4

interface Member { // 코틀린은 인터페이스에 추상 프로퍼티 선언을 넣을 수 있음
    val nickname: String
}

class PrivateMember(override val nickname: String) : Member // 주 생성자 안에 직접 프로퍼티를 선언한 간결한 구문

class SubscribingMember(val email: String) : Member {
    override val nickname: String
        get() = email.substringBefore('@') // 커스텀 게터를 사용하여 프로퍼티 설정, SubscribingMember는 nickname을 호출할때마다 매번 계산을 해서 가져옴
}

class FacebookMember(val accountId: Int) : Member {
    override val nickname = getFacebookName(accountId) // 객체 초기화 한번에 getFacebookName을 가져와 초기에 한번만 세팅

    private fun getFacebookName(accountId: Int): String {
        return accountId.toString()
    }
}

interface Member2 { // 코틀린은 인터페이스에 추상 프로퍼티 선언을 넣을 수 있음
    val email: String // 하위 클래스는 email은 무조건 오버라이드 해야함
    val nickname: String // nickname은 오버라이드 하지 않고 상속 할 수 있음
        get() = email.substringBefore('@')
}

class Worker(val name: String) {
    var address: String = "unspecified"
        set(value: String) {
            println("""
                Addresss was changed for $name:
                "$field" -> "$value".""".trimIndent()) // 뒷받침하는 필드 값 읽기
            field = value // 뒷받침하는 필드 값 변경하기
        }
}

class LengthCounter {
    var counter: Int = 0
        private set // 클래스 밖에서 이 프로퍼티의 값을 바꿀 수 없음
    fun addWord(word: String) {
        counter += word.length
    }
}

fun main(args: Array<String>) {
    println(PrivateMember("test@kotlinlang.org").nickname)
    println(SubscribingMember("test@kotlinlang.org").nickname)

    val worker = Worker("Alice")
    worker.address = "Elsenheimerstrasse 47" // 내부적으로 setter 메서드를 호출

    val lengthCounter = LengthCounter()
    lengthCounter.addWord("HI!")
    println(lengthCounter.counter)
}