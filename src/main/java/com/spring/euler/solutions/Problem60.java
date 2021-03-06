package com.spring.euler.solutions;

import com.spring.euler.helper.ListHelper;
import com.spring.euler.helper.MathsHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Problem60 {
    public static String run() {
        int result = -1;
        List<Integer> primes = MathsHelper.sieveOfEratosthenes(10000);
        Map<Integer, List<Integer>> concatMap = new HashMap<>();
        List<List<Integer>> concatPairs = ListHelper.generateCombinations(primes, 2)
                .stream()
                .filter(pair -> {
                    int concat = Integer.parseInt(String.valueOf(pair.get(0)) + pair.get(1));
                    int reverseConcat = Integer.parseInt(String.valueOf(pair.get(1)) + pair.get(0));
                    return MathsHelper.isPrime(concat) && MathsHelper.isPrime(reverseConcat);
                })
                .collect(Collectors.toList());

        primes.forEach(prime -> {
            List<Integer> concatPartners = concatPairs.stream()
                    .filter(pair -> pair.contains(prime))
                    .map(pair -> pair.get(0).equals(prime) ? pair.get(1) : pair.get(0))
                    .collect(Collectors.toList());

            concatMap.put(prime, concatPartners);
        });

        // Not very elegant, but it works, it makes sense, and it's reasonably quick
        for (int a: primes) {
            for (int b: concatMap.get(a)) {
                for (int c: concatMap.get(b)) {
                    if (!concatMap.get(c).contains(a)) { continue; }
                    for (int d: concatMap.get(c)) {
                        if (!concatMap.get(d).containsAll(List.of(a, b))) { continue; }
                        for (int e: concatMap.get(d)) {
                            if (!concatMap.get(e).containsAll(List.of(a, b, c))) { continue; }
                            result = a + b + c + d + e;
                        }
                    }
                }
            }
        }

        return String.valueOf(result);
    }
}