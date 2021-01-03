package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.ArrayList;
import java.util.List;

public final class Problem23 {
    private static final int LIMIT = 28123;

    public static String run() {
        List<Integer> abundant = new ArrayList<>();
        long sum = 0;

        for (int i = 2; i <= LIMIT; i++) {
            if (MathsHelper.isAbundant(i)) { abundant.add(i); }
        }

        boolean[] canBeWrittenAsAbundant = new boolean[LIMIT + 1];
        for (int i = 0; i < abundant.size(); i++) {
            for (int j = i; j < abundant.size(); j++) {
                if (abundant.get(i) + abundant.get(j) <= LIMIT) {
                    canBeWrittenAsAbundant[abundant.get(i) + abundant.get(j)] = true;
                } else { break; }
            }
        }

        for (int i = 1; i <= LIMIT; i++) {
            if (!canBeWrittenAsAbundant[i]) { sum += i; }
        }
        return String.valueOf(sum);
    }
}
