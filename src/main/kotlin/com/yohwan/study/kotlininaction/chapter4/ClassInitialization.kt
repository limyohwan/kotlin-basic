package com.yohwan.study.kotlininaction.chapter4

fun main(args: Array<String>) {
    val hwan = User4("요환") // isSubscribed 파라미터에 디폴트 값이 들어감
    println(hwan.isSubscribed)

    val gye = User4("계영", false) // 다양한 방법으로 사용 가능
    val hey = User4("혜원", isSubscribed = false)
}

class User(val nickname: String) // 클래스 이름 뒤에 오는 괄호로 둘러싸인 코드를 주 생성자(primary constructor)라고 함
// val은 이 파라미터에 상응하는 프로퍼티가 생성된다는 뜻임

class User2 constructor(_nickname: String) { // 파라미터가 하나만 있는 주 생성자
    val nickname: String

    init { // 초기화 블록
        nickname = _nickname
    }
}

class User3(_nickname: String) { // 파라미터가 하나만 있는 주 생성자
    val nickname = _nickname // 프로퍼티를 주 생성자의 파라미터로 초기화 함
}

class User4(val nickname: String, val isSubscribed: Boolean = true) // 생성자 파라미터에 대한 디폴트 값을 제공함

open class User5(val nickname: String) {}
class TwitterUser(nickname: String) : User5(nickname) {} // 클래스에 기반 클래스가 있다면 주 생성자에서 기반 클래스의 생성자를 호출해야 함, 기반 클래스를 초기화하려면 기반 클래스 이름 뒤에 괄호를 치고 생성자 인자를 넘기면 됨

open class Yohwan // 별도의 생성자를 정의하지 않으면 컴파일러가 자동으로 아무일을 하지 않는 인자가 없는 디폴트 생성자를 만듬
class DuplicatedYohwan: Yohwan() // Yohwan 생성자는 아무 인자도 받지 않지만 Yohwan을 상속한 하위 클래스는 반드시 Yohwan의 생성자를 호출해야 함

class Secretive private constructor() {} // 이 클래스의 (유일한) 주 생성자는 비공개임, 그러므로 외부에서 인스턴스화 할 수 없음