package com.spring.euler.helper;

import java.math.BigInteger;

public abstract class BigIntegerHelper {

    public static BigInteger lowestCommonMultiple(BigInteger x, BigInteger y) {
        return x.divide(x.gcd(y)).multiply(y);
    }

    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (long i = 2; i <= n; i++) { result = result.multiply(BigInteger.valueOf(i)); }
        return result;
    }

    public static BigInteger binomial(int n, int k) {
        return factorial(n).divide((factorial(k).multiply(factorial(n - k))));
    }
}
