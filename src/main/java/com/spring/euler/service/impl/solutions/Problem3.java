package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem3 {
    public static Long run() {
        long result = 600851475143L;
        int prime = 2;
        while (!MathsHelper.isPrime(result)) {
            if (result % prime == 0) { result /= prime; } else { prime = MathsHelper.findNextPrime(prime); }
        }
        return result;
    }
}
