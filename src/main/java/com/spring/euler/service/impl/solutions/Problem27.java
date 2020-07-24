package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.MathsHelper;

public class Problem27 {
    public static Integer run() {
        int result = 0;
        int maxN = 0;
        for (int a = -1000; a < 1000; a++) {
            for (int b = -1000; b < 1000; b++) {
                boolean prime = true;
                int n = 0;
                while (prime) {
                    int formula = (n * n) + (a * n) + b;
                    if (!MathsHelper.isPrime(formula)) { prime = false; }
                    n++;
                }
                if (n > maxN) { maxN = n; result = a * b; }
            }
        }
        return result;
    }
}
