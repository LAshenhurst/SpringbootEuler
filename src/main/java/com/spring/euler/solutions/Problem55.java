package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.stream.IntStream;

public final class Problem55 {
    public static String run() {
        long result = IntStream.rangeClosed(10, 10000).boxed().filter(MathsHelper::isLychrel).count();
        return String.valueOf(result);
    }
}
