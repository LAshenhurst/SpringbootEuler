package com.spring.euler.solutions;

public final class Problem63 {
    // 10 ^ x will always have x + 1 digits
    // 9 ^ x will always have x digits, up to x = 21, after which it has less than x digits
    public static String run() {
        int result = 1;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 22; j++) {
                long pow = (long)Math.pow(i, j);
                int powLength = String.valueOf(pow).length();
                if (powLength == j) { result++; }
            }
        }
        return String.valueOf(result);
    }
}
