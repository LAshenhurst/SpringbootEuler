package com.spring.euler.solutions;

import com.spring.euler.helper.StringHelper;

import java.util.List;

public final class Problem43 {
    private final static List<Integer> primes = List.of(2, 3, 5, 7, 11, 13, 17);

    public static String run() {
        long result =  StringHelper.permutations("0123456789").stream()
                .map(Long::parseLong)
                .filter(x -> x >= 1023456789)
                .filter(Problem43::substringProperty)
                .reduce(0L, Long::sum);

        return String.valueOf(result);
    }

    private static Boolean substringProperty(Long value) {
        String[] stringValue = String.valueOf(value).split("");
        for (int i = 0; i < primes.size(); i++) {
            Integer testValue = Integer.parseInt(stringValue[i + 1] + stringValue[i + 2] + stringValue[i + 3]);
            if (testValue % primes.get(i) != 0) { return false; }
        }
        return true;
    }
}
