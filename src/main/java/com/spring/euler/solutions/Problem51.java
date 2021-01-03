package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Problem51 {
    public static String run() {
        final List<Integer> primes = MathsHelper.sieveOfEratosthenes(1000000);
        List<Integer> digits;
        List<Integer> repeatLocations;
        List<Integer> family;
        for (Integer prime: primes) {
            if (prime < 100000) { continue; }
            digits = MathsHelper.toDigits(prime);
            for (int repeatDigit: List.of(0, 1, 2)) {
                if (Collections.frequency(digits, repeatDigit) == 3 && digits.lastIndexOf(repeatDigit) != 5) {
                    repeatLocations = findRepeatLocations(digits, repeatDigit);
                    family = generateFamily(digits, repeatLocations);
                    if (family.stream().filter(MathsHelper::isPrime).count() == 8) { return String.valueOf(Collections.min(family)); }
                }
            }
        }
        return null;
    }

    private static List<Integer> generateFamily(List<Integer> digits, List<Integer> repeatLocations) {
        List<Integer> family = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int location: repeatLocations) { digits.set(location, i); }
            if (digits.get(0) != 0) { family.add(MathsHelper.parseDigits(digits)); }
        }
        return family;
    }

    private static List<Integer> findRepeatLocations(List<Integer> digits, Integer repeat) {
        List<Integer> repeatLocations = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (digits.get(i).equals(repeat)) { repeatLocations.add(i); }
        }
        return repeatLocations;
    }
}
