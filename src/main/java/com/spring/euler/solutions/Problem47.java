package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.HashSet;
import java.util.stream.Stream;

public final class Problem47 {
    public static String run() {
        for (int i = 644; ; i++) {
            boolean criteriaMatch = Stream.of(i, i + 1, i + 2, i + 3)
                    .map(x -> new HashSet<>(MathsHelper.primeFactors(x)))
                    .allMatch(primeSet -> primeSet.size() == 4);
            if (criteriaMatch) { return String.valueOf(i); }
        }
    }
}
