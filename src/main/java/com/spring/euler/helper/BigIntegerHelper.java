package com.spring.euler.helper;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
public abstract class BigIntegerHelper {

    public static BigInteger lowestCommonMultiple(BigInteger x, BigInteger y) {
        return x.divide(x.gcd(y)).multiply(y);
    }

    public static BigInteger factorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (long i = 2; i <= n; i++) { result = result.multiply(BigInteger.valueOf(i)); }
        return result;
    }
}
