package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem10 {
    public static Long run() {
        return MathsHelper.sieveOfEratosthenes(2000000)
                .stream()
                .map(Long::valueOf)
                .reduce(0L, Long::sum);
    }
}
