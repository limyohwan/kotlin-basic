package com.yohwan.study.kotlininaction.chapter4

import javax.naming.Context
import javax.swing.text.AttributeSet

open class Viewer {
    constructor(ctx: Context) {

    }

    constructor(ctx: Context, attr: AttributeSet) { // 부 생성자

    }
}

class ViewerButton: Viewer {
//    constructor(ctx: Context): super(ctx) { // super 키워드를 통해 상위 클래스의 생성자 호출, 상위 클래스 생성자에게 객체 생성을 위임함
//
//    }
//
//    constructor(ctx: Context): this(ctx, attr) { // this 키워드를 사용해서 자신의 다른 생성자를 호출하게 할 수 있음
//
//    }

    constructor(ctx: Context, attr: AttributeSet): super(ctx, attr) { // 상위 클래스의 생성자 호출

    }
}