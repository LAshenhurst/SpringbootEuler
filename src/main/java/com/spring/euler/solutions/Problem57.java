package com.spring.euler.solutions;

import java.math.BigInteger;

public final class Problem57 {
    public static String run() {
        BigInteger[] fractions = new BigInteger[2];
        fractions[0] = BigInteger.ZERO;
        fractions[1] = BigInteger.ONE;
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            BigInteger temp = fractions[1].multiply(BigInteger.valueOf(2)).add(fractions[0]);
            fractions[0] = fractions[1];
            fractions[1] = temp;
            if (fractions[0].add(fractions[1]).toString().length() > fractions[1].toString().length()) { count++; }
        }
        return String.valueOf(count);
    }
}
