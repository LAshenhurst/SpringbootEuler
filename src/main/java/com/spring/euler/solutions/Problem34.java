package com.spring.euler.solutions;

import java.util.Arrays;

public abstract class Problem34 {
    public static String run() {
        int[] factorials = { 1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };
        int result = 0;
        for (int i = 3; i < 2540160; i++) {
            int sum = Arrays.stream(String.valueOf(i).split(""))
                    .map(Integer::parseInt)
                    .map(digit -> factorials[digit])
                    .reduce(0, Integer::sum);
            if (sum == i) { result += i; }
        }
        return String.valueOf(result);
    }
}
