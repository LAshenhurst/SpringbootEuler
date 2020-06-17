package com.spring.Euler.helper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MathsHelper {

    public static boolean isPrime(long n) {
        if (n <= 2) { return false; }
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) { return false; }
        }
        return true;
    }

    public static int findNextPrime(int n) {
        if (n % 2 == 0) { n += 1; } else { n += 2; }
        while (!isPrime(n)) { n += 2; }
        return n;
    }

    public static boolean isPalindrome(int n) {
        String val = String.valueOf(n);
        return new StringBuilder(val).reverse().toString().equals(val);
    }

    public static Integer lowestCommonMultiple(Integer x, Integer y) {
        return (x * y) / greatestCommonDivisor(x , y);
    }

    public static Integer greatestCommonDivisor(Integer x, Integer y) {
        if (x.equals(y)) { return x; }
        else { return x > y ? greatestCommonDivisor(x - y, y) : greatestCommonDivisor(x, y - x); }
    }
}
