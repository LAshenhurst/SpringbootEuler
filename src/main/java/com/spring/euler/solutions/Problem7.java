package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public final class Problem7 {
    public static String run() {
        int prime = 2;
        for (int count = 1; count < 10001; count++) { prime = MathsHelper.findNextPrime(prime); }
        return String.valueOf(prime);
    }
}