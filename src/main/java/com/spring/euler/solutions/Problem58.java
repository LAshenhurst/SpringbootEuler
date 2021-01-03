package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public final class Problem58 {
    public static String run() {
        int primes = 0;
        for (int n = 1; ; n += 2) {
            for (int i = 0; i < 4; i++) {
                if (MathsHelper.isPrime(n * n - i * (n - 1))) { primes++; }
            }
            if (n > 1 && primes * 10 < n * 2 - 1) { return String.valueOf(n); }
        }
    }
}
