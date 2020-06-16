package com.euler.Euler.service.impl.solutions;

import java.util.stream.IntStream;

public class FirstTenSolutions {
    public static Integer One() {
        return IntStream.rangeClosed(0, 999).filter(x -> x % 3 == 0 || x % 5 == 0).sum();

    }

    public static Integer Two() {
        int firstFib = 1;
        int secondFib = 2;
        int storage;
        int result = 0;
        while (secondFib < 4000000) {
            if (secondFib % 2 == 0) { result += secondFib; }
            storage = firstFib;
            firstFib = secondFib;
            secondFib += storage;
        }
        return result;
    }
}
