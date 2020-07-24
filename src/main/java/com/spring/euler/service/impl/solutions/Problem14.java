package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.MathsHelper;

public class Problem14 {
    public static Long run() {
        long result = -1L;
        int maxCollatz = -1;
        for (long i = 1L; i < 1000000; i++) {
            long collatzValue = i;
            int collatzChain = 0;
            while (collatzValue != 1) {
                collatzValue = MathsHelper.collatz(collatzValue);
                collatzChain++;
                if (MathsHelper.isInteger(MathsHelper.log2(collatzValue))) {
                    collatzChain += MathsHelper.log2(collatzValue).intValue();
                    collatzValue = 1L;
                }
            }
            if (collatzChain > maxCollatz) {
                result = i;
                maxCollatz = collatzChain;
            }
        }
        return result;
    }
}
