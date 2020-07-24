package com.spring.euler.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static List<Integer> sieveOfEratosthenes(Integer target) {
        final boolean[] nonPrime = new boolean[target + 1];
        for (int i = 2; i <= Math.sqrt(target); i++) {
            if (!nonPrime[i]) {
                for (int j = i * 2; j <= target; j += i) { nonPrime[j] = true; }
            }
        }
        final List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= target; i++) { if (!nonPrime[i]) primes.add(i); }
        return primes;
    }

    public static Integer triangleNumber(int n) {
        return ((n * n) + n) / 2;
    }

    public static List<Integer> findDivisors(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                result.add(i);
                result.add(n / i);
            }
        }
        return result;
    }

    public static Boolean isAbundant(int n) {
        return (findDivisors(n).stream().reduce(0, Integer::sum) - n) > n;
    }

    public static Long collatz(Long n) {
        return n % 2 == 0 ? n / 2 : ((3 * n) + 1);
    }

    public static Double log2(Long x) {
        return Math.log(x) / Math.log(2);
    }

    public static Boolean isInteger (Double n) {
        return n == Math.floor(n) && !Double.isInfinite(n);
    }

    public static Long binomial(int n, int k)
    {
        if (k > n - k) { k = n - k; }
        long result = 1;
        for (int i = 1, m = n; i <= k; i++, m--) { result *= m / i; }
        return result;
    }

    public static Integer recurringCycle(int denominator) {
        Map<Integer, Integer> decimals = new HashMap<>();
        int numerator = 1;
        int position = 0;
        while (numerator != 0) {
            position++;
            numerator *= 10;
            if (decimals.containsKey(numerator)) { return position - decimals.get(numerator); }
            else { decimals.put(numerator, position); }
            numerator %= denominator;
        }
        return 0;
    }
}
