package com.yohwan.study.kotlininaction.chapter6;

import java.io.File;
import java.util.List;

public interface FileContentProcessor {
    void processContests(File path, byte[] binaryContents, List<String> textContents);
}
