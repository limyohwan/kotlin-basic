package com.yohwan.grammar.kotlininaction.chapter4

fun main(args:Array<String>) {
    val client = Client("오현석", 111)
    val client2 = Client("오현석", 111)
    println(client)
    println(client == client2) // equals를 오버라이드하기 전에는 false이지만 오버라이드한 후는 true를 반환홤(== 연산자 = 동등성, equals = 동일성)

    val processed = hashSetOf(client)
    println(processed.contains(client2)) // hashCode를 오버라이드하기 전에는 false를 반환하지만 오버라이드한 후에는 true를 반환함

    val newClient = NewClient("오현석", 111)
    newClient.copy()
}

class Client(val name: String, val postalCode: Int) {
    override fun toString() = "Client(name=$name, postalCode=$postalCode)"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (name != other.name) return false
        if (postalCode != other.postalCode) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + postalCode
        return result
    }

    fun copy(name: String = this.name, postalCode: Int = this.postalCode) = Client(name, postalCode) // 객체 복사를 위한 copy, 데이터 클래스는 기본 제공해줌
}

data class NewClient(val name: String, val postalCode: Int) // data 클래스는 위의 Client가 오버라이드하던 것들(toString, equals, hashCode)을 전부 자동 생성 해줌
// 데이터 클래스의 변수가 꼭 val일 필요는 없지만 데이터 클래스의 모든 프로퍼티를 읽기 전용으로 만들어 불변 클래스로 만드는 것을 권장함