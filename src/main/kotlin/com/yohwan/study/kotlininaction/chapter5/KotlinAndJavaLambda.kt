package com.yohwan.study.kotlininaction.chapter5

fun main(args: Array<String>) {
//    val button = Button()
//    button.postponeComputation(1000) { println(42) } // 프로그램 전체에서 Runnable의 인스턴스는 단 하나만 만들어짐
//
//    button.postponeComputation(1000, object : Runnable { // 객체식을 함수형 인터페이스 구현으로 넘김
//        override fun run() {
//            println(42)
//        }
//    })
//
//    val runnable = Runnable { println(42) } // Runnable은 SAM 생성자, 전역 변수로 컴파일되므로 프로그램안에 단 하나의 인스턴스만 존재함
//    fun handleComputation() {
//        button.postponeComputation(1000, runnable)// 모든 handleComputation 호출에 같은 객체를 사용함
//    }
//
//    fun handleComputation2(id: String) { // 람다 안에서 id 변수를 포획
//        button.postponeComputation(1000) { println(id) } // handleComputation을 호출할 때마다 새로 Runnable 인스턴스를 만듬
//    }
    // 대부분의 경우 람다와 자바 함수형 인터페이스 사이의 변환은 자동으로 이뤄짐

    createAllDoneRunnable().run()
}

fun createAllDoneRunnable() : Runnable { // SAM 생성자 = 람다를 함수형 인터페이스의 인스턴스로 변환할 수 있게 컴파일러가 자동으로 생성한 함수
    return Runnable { println("All done!") }
}