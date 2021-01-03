package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public final class Problem3 {
    public static String run() {
        long result = 600851475143L;
        int prime = 2;
        while (!MathsHelper.isPrime(result)) {
            if (result % prime == 0) { result /= prime; } else { prime = MathsHelper.findNextPrime(prime); }
        }
        return String.valueOf(result);
    }
}
