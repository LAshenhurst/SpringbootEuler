package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem26 {
    public static Integer run() {
        int result = 0;
        int maxRecurring = 0;
        for (int i = 2; i < 1000; i++) {
            int reciprocal = MathsHelper.recurringCycle(i);
            if (reciprocal > maxRecurring) { result = i; maxRecurring = reciprocal; }
        }
        return result;
    }
}
