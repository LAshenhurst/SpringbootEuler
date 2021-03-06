package com.spring.euler.solutions;

public final class Problem28 {
    public static String run() {
        int result = 1;
        for (int i = 1, step = 2; i < (1001 * 1001); i += (step * 4), step += 2) {
            result += (4 * i) + (10 * step);
        }
        return String.valueOf(result);
    }
}
