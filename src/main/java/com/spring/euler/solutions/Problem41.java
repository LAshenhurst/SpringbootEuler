package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem41 {
    public static String run() {
        for (long i = 987654321; ; i -= 2) {
            if (MathsHelper.isPandigital(i) && MathsHelper.isPrime(i)) { return String.valueOf(i); }
        }
    }
}
