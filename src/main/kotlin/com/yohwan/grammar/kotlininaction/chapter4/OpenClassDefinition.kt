package com.yohwan.grammar.kotlininaction.chapter4

// 취약한 기반 클래스 문제 = 하위 클래스가 기반 클래스에 대해 가졌던 가정이 기반 클래스를 변경함으로 깨져버린 경우
// 상속을 위한 설계와 문서를 갖추거나 그럴 수 없다면 상속을 금지하라 -> 코틀린은 이 뜻에 따라 기본적으로 모든 클래스를 final로 만듬

open class RichButton : Clickable { // open 클래스이므로 다른 클래스가 상속 가능
    fun disable() { // final 메서드임으로 하위 클래스가 오버라이드할 수 없음

    }

    open fun animate() { // open 메서드임으로 하위 클래스가 오버라이드할 수 있음

    }

    override fun click() { // 이 함수는 열려있는 메서드를 오버라이드하며 오버라이드한 메서드는 기본적으로 open 상태임

    }

//    final override fun click() { // 오버라이드 메서드 앞에 final를 추가하여 하위 클래스에서 오버라이드할 수 없게 만들 수 있음
//
//    }
}

// 코틀린은 기본적인 상속 가능 상태를 final로 함으로써 스마트 캐스트가 가능해짐 -> 스마트캐스트는 변경될 수 없는 변수에만 사용 가능함

abstract class Animated { // 추상 클래스로 인스턴스를 만들 수 없음
    abstract fun animate() // 추상 함수로 구현이 없으며 하위 클래스에서 반드시 오버라이드해야 함

    open fun stopAnimating() { // 추상클래스에 속하더라도 비추상 함수는 기본적으로 final이지만 원한다면 open으로 오버라이드를 허용할 수 있음

    }

    fun animateTwice() {

    }
}

// 클래스 내 상속 제어 변경자 의미
// final 오버라이드할 수 없음, 클래스 멤버의 기본 변경자임
// open 오버라이드할 수 있음, 반드시 open을 명시해야 오버라이드할 수 있음
// abstract 반드시 오버라이드해야 함, 추상 클래스의 멤버에만 이 변경자를 붙일수 있으며 추상 멤버에는 구현이 있으면 안됨
// override 상위 클래스나 상위 인스턴스의 멤버를 오버라이드하는 중, 오버라이드하는 멤버는 기본적으로 open 상태이며 하위 클래스의 오버라이드를 금지하려면 final를 명시해야 함

// 가시성 변경자   클래스 멤버           최상위 선언
// public(기본) 모든 곳에서 볼 수 있음, 모든 곳에서 볼 수 있음
// internal 같은 모듈 안에서만 볼 수 있음, 같은 모듈 안에서만 볼 수 있음
// protected 하위 클래스 안에서만 볼 수 있음, (최상위 선언에 적용 불가)
// private 같은 클래스 안에서만 볼 수 있음, 같은 파일 안에서만 볼 수 있음

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey")
    protected fun whisper() = println("lets talk")
}

// giveSpeech의 가시성을 internal로 줄이던가 TalkativeButton의 가시성을 public으로 올려야 함
fun TalkativeButton.giveSpeech() { // public 함수인 giveSpeech 안에서 그보다 가시성이 더 낮은 타입인 TalkativeButton을 참조하지 못함
                                   // -> 클래스의 기반 타입 목록에 들어있는 타입이나 제네릭 클래스의 타입 파라미터에 들어있는 타입의 가시성이 그 클래스 자신의 가시성과 같거나 더 높아야되며 메서드의 시그니처에 사용된 모든 타입의 가시성은 그 메서드의 가시성과 같거나 더 높아야 함
    yell() // yell은 TalkativeButton의 private 멤버임
    whisper() // whisper는 TalkativeButton의 protected 멤버임 -> protected 멤버는 오직 어떤 클래스나 그 클래스를 상속한 클래스 안에서만 보임
}
// 클래스를 확장한 함수는 그 클래스의 private이나 protected 멤버에 접근할 수 없음