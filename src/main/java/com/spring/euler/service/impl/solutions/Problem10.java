package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.MathsHelper;

public class Problem10 {
    public static Long run() {
        return MathsHelper.sieveOfEratosthenes(2000000)
                .stream()
                .map(Long::valueOf)
                .reduce(0L, Long::sum);
    }
}
