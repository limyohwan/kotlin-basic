package com.yohwan.grammar.kotlininaction.chapter6;

public class ExamplePerson {
    private final String name; // 코틀린에서는 자바코드를 확인할때 @Nullable, @NotNull이라는 애노테이션이 없으면 String? 혹은 String 둘 다 사용할 수 있음

    public ExamplePerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
