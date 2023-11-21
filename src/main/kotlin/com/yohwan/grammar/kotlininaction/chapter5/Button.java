package com.yohwan.grammar.kotlininaction.chapter5;

import com.yohwan.grammar.kotlininaction.chapter4.View;

public class Button {
    public static void main(String[] args) {
        Button button = new Button();
        button.setOnclickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void setOnclickListener(OnClickListener l) {}

    public void postponeComputation(int delay, Runnable computation) {}
}