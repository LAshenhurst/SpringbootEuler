package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.BigIntegerHelper;

import java.math.BigInteger;

public class Problem5 {
    public static BigInteger run() {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= 20; i++) { result = BigIntegerHelper.lowestCommonMultiple(BigInteger.valueOf(i), result); }
        return result;
    }
}
