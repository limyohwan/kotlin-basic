package com.yohwan.study.kotlininaction.chapter5;

import com.yohwan.study.kotlininaction.chapter4.View;

public interface OnClickListener { // 함수형 인터페이스, SAM(single abstract method) 인터페이스
    void onClick(View v);
}
