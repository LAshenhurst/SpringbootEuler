package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public final class Problem10 {
    public static String run() {
        return String.valueOf(MathsHelper.sieveOfEratosthenes(2000000)
                .stream()
                .map(Long::valueOf)
                .reduce(0L, Long::sum));
    }
}
