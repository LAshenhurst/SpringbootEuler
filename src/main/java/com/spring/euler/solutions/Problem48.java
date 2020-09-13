package com.spring.euler.solutions;

import java.math.BigInteger;

public abstract class Problem48 {
    public static String run() {
        BigInteger modulus = BigInteger.TEN.pow(10);
        BigInteger sum = BigInteger.ZERO;
        for (int i = 1; i <= 1000; i++) {
            sum = sum.add(BigInteger.valueOf(i).modPow(BigInteger.valueOf(i), modulus));
        }
        return sum.mod(modulus).toString();
    }
}
