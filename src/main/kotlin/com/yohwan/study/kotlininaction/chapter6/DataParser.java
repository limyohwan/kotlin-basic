package com.yohwan.study.kotlininaction.chapter6;

import java.util.List;

public interface DataParser<T> {
    void parseDate(String input, List<T> output, List<String> errors);
}
