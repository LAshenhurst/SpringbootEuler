package com.spring.euler.solutions;

import java.util.List;

public final class Problem40 {
    public static String run() {
        List<Integer> indices = List.of(1, 10, 100, 1000, 10000, 100000, 1000000);
        StringBuilder decimal = new StringBuilder();
        for (int i = 1; ; i++) {
            if (decimal.length() > 1000000) { break; }
            decimal.append(i);
        }
        int result = indices.stream()
                .map(index -> Character.getNumericValue(decimal.charAt(index - 1)))
                .reduce(1, (a, b) -> a * b);
        return String.valueOf(result);
    }
}
