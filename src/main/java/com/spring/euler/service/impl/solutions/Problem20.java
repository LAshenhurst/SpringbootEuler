package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.BigIntegerHelper;

import java.util.Arrays;

public abstract class Problem20 {
    public static Integer run() {
        return Arrays.stream(BigIntegerHelper.factorial(100).toString().split(""))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }
}
