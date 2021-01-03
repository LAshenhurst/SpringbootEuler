package com.spring.euler.solutions;

import com.spring.euler.helper.BigIntegerHelper;

import java.math.BigInteger;

public final class Problem5 {
    public static String run() {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= 20; i++) { result = BigIntegerHelper.lowestCommonMultiple(BigInteger.valueOf(i), result); }
        return result.toString();
    }
}
