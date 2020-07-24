package com.spring.euler.service.impl.solutions;

import java.math.BigInteger;
import java.util.Arrays;

public abstract class Problem16 {
    public static Integer run() {
        return Arrays.stream(BigInteger.TWO.pow(1000).toString().split(""))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }
}
