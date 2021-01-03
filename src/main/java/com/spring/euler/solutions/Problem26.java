package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public final class Problem26 {
    public static String run() {
        int result = 0;
        int maxRecurring = 0;
        for (int i = 2; i < 1000; i++) {
            int reciprocal = MathsHelper.recurringCycle(i);
            if (reciprocal > maxRecurring) { result = i; maxRecurring = reciprocal; }
        }
        return String.valueOf(result);
    }
}
