package com.spring.euler.solutions;

import java.math.BigInteger;
import java.util.Arrays;

public final class Problem16 {
    public static String run() {
        return String.valueOf(Arrays.stream(BigInteger.TWO.pow(1000).toString().split(""))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum));
    }
}
