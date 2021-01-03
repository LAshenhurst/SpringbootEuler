package com.spring.euler.solutions;

import com.spring.euler.helper.BigIntegerHelper;

import java.util.Arrays;

public final class Problem20 {
    public static String run() {
        return String.valueOf(Arrays.stream(BigIntegerHelper.factorial(100).toString().split(""))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum));
    }
}
