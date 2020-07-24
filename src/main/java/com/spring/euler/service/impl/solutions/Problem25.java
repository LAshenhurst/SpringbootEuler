package com.spring.euler.service.impl.solutions;

import java.math.BigInteger;

public class Problem25 {
    public static Integer run() {
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
        return index;
    }
}