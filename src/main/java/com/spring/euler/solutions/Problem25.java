package com.spring.euler.solutions;

import java.math.BigInteger;

public abstract class Problem25 {
    public static String run() {
        BigInteger x = BigInteger.ONE;
        BigInteger y = BigInteger.ONE;
        BigInteger storage;
        int index = 2;
        while (y.toString().length() != 1000) {
            storage = y;
            y = y.add(x);
            x = storage;
            index++;
        }
        return String.valueOf(index);
    }
}
