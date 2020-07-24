package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem7 {
    public static Integer run() {
        int prime = 2;
        for (int count = 1; count < 10001; count++) { prime = MathsHelper.findNextPrime(prime); }
        return prime;
    }
}