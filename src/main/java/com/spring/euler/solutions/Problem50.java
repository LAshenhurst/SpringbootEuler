package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.List;

public abstract class Problem50 {
    public static String run() {
        List<Integer> primes = MathsHelper.sieveOfEratosthenes(1000000);
        int result = 953; int resultTerms = 21;
        boolean done = false;
        for (int i = 0; !done; i++) {
            for (int j = resultTerms; ; j++) {
                int primesSum = primes.subList(i, i + j).stream().reduce(0, Integer::sum);
                if (primesSum > 1000000) {
                    if (j == resultTerms) { done = true; }
                    break;
                }
                if (MathsHelper.isPrime(primesSum) && j > resultTerms) { result = primesSum; resultTerms = j; }
            }
        }
        return String.valueOf(result);
    }
}
