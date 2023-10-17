package com.yohwan.grammar.rectangle

import com.yohwan.grammar.kotlininaction.createRandomRectangle

fun main(args: Array<String>) {
    println(createRandomRectangle().isSquare)
}

// 코틀린에서는 함수 임포트와 클래스 임포트의 차이가 없으며 모든 선언을 import로 가져올 수 있음
// 최상위 함수는 그 이름을 써서 임포트할 수 있음
// com.yohwan.grammar.kotlininaction.* 는 최상위에 정의된 함수나 프로퍼티 모두를 불러움

// Java 디렉터리 구조
// geometry(package)
// -> example(package)
// -> -> Main(class)
// -> shapes(package)
// -> -> Rectangle(class)
// -> -> RectangleUtil(class)

// 코틀린 디렉터리 구조
// geometry(package)
// -> example(kt)
// -> shapes(kt)

// 코틀린은 여러 클래스를 한파일로 넣을 수 있음
// 하지만 대부분의 경우 자바와 같이 패키지별로 티렉터리를 구성하는 편이 나음 -> 자바 마이그레이션시 문제 발생 가능
// 그래도 소스코드가 작은경우 한 파일에 넣는 것을 주저하면 안됨