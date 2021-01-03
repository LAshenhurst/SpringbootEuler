package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.Collections;
import java.util.List;

public final class Problem52 {
    public static String run() {
        List<Integer> digits;
        List<Integer> testDigits;
        for (int i = 125874; ; i++)
        {
            digits = MathsHelper.toDigits(i);
            Collections.sort(digits);
            for (int j = 2; j <= 6; j++)
            {
                testDigits = MathsHelper.toDigits(i * j);
                Collections.sort(testDigits);
                if (!testDigits.equals(digits)) { break; }
                else if (j == 6) { return String.valueOf(i); }
            }
        }
    }
}
