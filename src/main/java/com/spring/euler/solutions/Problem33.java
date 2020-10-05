package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem33 {
    public static String run() {
        int resultNumerator = 1;
        int resultDenominator = 1;
        for (int i = 10; i <= 100; i++) {
            for (int j = 10; j < i; j++) {
                int n0 = j % 10; int n1 = j / 10;
                int d0 = i % 10; int d1 = i / 10;
                if (n1 == d0 && n0 * i == d1 * j || n0 == d1 && n1 * i == d0 * j) {
                    resultNumerator *= j;
                    resultDenominator *= i;
                }
            }
        }
        int result = resultDenominator / MathsHelper.greatestCommonDivisor(resultNumerator, resultDenominator);
        return String.valueOf(result);
    }
}
