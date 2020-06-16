package com.spring.Euler.helper;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

@Slf4j
public class BigIntegerHelper {

    public static BigInteger lowestCommonMultiple(BigInteger x, BigInteger y) {
        return x.divide(x.gcd(y)).multiply(y);
    }
}
