package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class Problem21 {
    public static Integer run() {
        List<Integer> amicableNumbers = new ArrayList<>();
        for (int i = 1; i < 10000; i++) {
            if (amicableNumbers.contains(i)) { continue; }
            int iDivisors = MathsHelper.findDivisors(i).stream().reduce(0, Integer::sum) - i;
            int jDivisors = MathsHelper.findDivisors(iDivisors).stream().reduce(0, Integer::sum) - iDivisors;
            if (i != iDivisors && i == jDivisors) { amicableNumbers.addAll(List.of(i, iDivisors)); }
        }
        return amicableNumbers.stream().reduce(0, Integer::sum);
    }
}
