package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;
import com.spring.euler.helper.StringHelper;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Problem49 {
    public static String run() {
        for (int i = 1488; i < 10000; i++) {
            if (i == 4817 || i == 8147 || !MathsHelper.isPrime(i)) { continue; }
            List<Integer> primePermutations = StringHelper.permutations(String.valueOf(i))
                    .stream()
                    .map(Integer::parseInt)
                    .filter(MathsHelper::isPrime)
                    .collect(Collectors.toList());
            if (primePermutations.contains(i + 3330) && primePermutations.contains(i + 6660)) {
                return String.valueOf(i) + (i + 3330) + (i + 6660);
            }
        }
        return null;
    }
}
