package com.spring.euler.solutions;

import java.util.Arrays;

public abstract class Problem30 {
    public static String run() {
        int result = 0;
        for (int i = 2; i < 1000000; i++) {
            Double sum = Arrays.stream(String.valueOf(i).split(""))
                    .map(Integer::parseInt)
                    .map(x -> Math.pow(x, 5))
                    .reduce(0.0, Double::sum);
            if (sum == i) { result += i; }
        }
        return String.valueOf(result);
    }
}