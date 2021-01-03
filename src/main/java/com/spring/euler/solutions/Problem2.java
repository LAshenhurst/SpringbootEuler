package com.spring.euler.solutions;

public final class Problem2 {
    public static String run() {
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
        return String.valueOf(result);
    }
}
