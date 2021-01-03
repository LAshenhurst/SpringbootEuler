package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.math.BigInteger;

public final class Problem56 {
    public static String run() {
        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                BigInteger pow = BigInteger.valueOf(i).pow(j);
                int digitSum = MathsHelper.digitSum(pow.toString());
                if (digitSum > result) { result = digitSum; }
            }
        }
        return String.valueOf(result);
    }
}