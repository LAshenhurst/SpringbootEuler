package com.spring.euler.solutions;

public abstract class Problem9 {
    public static String run() {
        int result = -1;
        for (int c = 1000; c > 0; c--) {
            for (int b = 0; b < c; b++) {
                for (int a = 0; a < b; a++) {
                    int sum = a + b + c;
                    if (sum == 1000 && Math.pow(a, 2) + Math.pow(b, 2) == Math.pow(c, 2)) {
                        result = a * b * c;
                        break;
                    }
                }
            }
        }
        return String.valueOf(result);
    }
}
