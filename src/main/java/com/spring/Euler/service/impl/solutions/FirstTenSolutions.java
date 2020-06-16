package com.spring.Euler.service.impl.solutions;

import com.spring.Euler.helper.BigIntegerHelper;
import com.spring.Euler.helper.MathsHelper;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class FirstTenSolutions {
    public static Integer One() { return IntStream.rangeClosed(0, 999).filter(x -> x % 3 == 0 || x % 5 == 0).sum(); }

    public static Integer Two() {
        int firstFib = 1;
        int secondFib = 2;
        int storage;
        int result = 0;
        while (secondFib < 4000000) {
            if (secondFib % 2 == 0) { result += secondFib; }
            storage = firstFib;
            firstFib = secondFib;
            secondFib += storage;
        }
        return result;
    }

    public static long Three() {
        long result = 600851475143L;
        int prime = 2;
        while (!MathsHelper.isPrime(result)) {
            if (result % prime == 0) { result /= prime; } else { prime = MathsHelper.findNextPrime(prime); }
        }
        return result;
    }

    public static Integer Four() {
        int result = -1;
        for (int i = 100; i < 1000; i++) {
            for (int j = 100; j < 1000; j++) {
                if (MathsHelper.isPalindrome(i * j) && i * j > result) { result = i * j; }
            }
        }
        return result;
    }

    public static BigInteger Five() {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= 20; i++) { result = BigIntegerHelper.lowestCommonMultiple(BigInteger.valueOf(i), result); }
        return result;
    }
}
