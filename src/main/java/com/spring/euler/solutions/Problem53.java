package com.spring.euler.solutions;

import com.spring.euler.helper.BigIntegerHelper;

import java.math.BigInteger;

public final class Problem53 {
    public static String run() {
        int count = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < i; j++) {
                if (BigIntegerHelper.binomial(i, j).subtract(BigInteger.valueOf(1000000)).signum() == 1) { count++; }
            }
        }
        return String.valueOf(count);
    }
}
