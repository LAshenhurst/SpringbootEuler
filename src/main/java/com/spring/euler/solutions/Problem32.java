package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class Problem32 {
    public static String run() {
        List<Integer> pandigitals = new ArrayList<>();
        for (int i = 0; i <= 10000; i++) {
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    String value = String.valueOf(i) + j + i / j;
                    if (value.length() == 9 && MathsHelper.isPandigital(Long.parseLong(value)) && !pandigitals.contains(i)) { pandigitals.add(i); }
                }
            }
        }
        return String.valueOf(pandigitals.stream().reduce(0, Integer::sum));
    }
}
