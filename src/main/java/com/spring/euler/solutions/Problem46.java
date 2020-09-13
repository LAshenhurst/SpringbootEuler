package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class Problem46 {
    public static Integer run() {
        List<Integer> possiblePrimes = new ArrayList<>();
        for (int i = 9; ; i += 2) {
            if (MathsHelper.isPrime(i)) { continue; }
            possiblePrimes.clear();
            for (int j = 1; 2 * (j * j) < i; j++) { possiblePrimes.add(i - (2 * (j * j))); }
            if (possiblePrimes.stream().noneMatch(MathsHelper::isPrime)) { return i; }
        }
    }
}
