package com.spring.euler.helper;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public abstract class MathsHelper {
    public static boolean isPrime(long n) {
        if (n < 2) { return false; }
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
    public static Boolean isTriangle(int t) {
        double root = Math.sqrt((t * 8.0) + 1);
        return isInteger((root + 1) / 2.0);
    }

    public static Integer pentagonalNumber(int n) { return (n * (3 * n - 1) / 2); }
    public static Boolean isPentagonal(int p) {
        double root = Math.sqrt((24.0 * p) + 1);
        return isInteger((root + 1) / 6.0);
    }

    public static Integer hexagonalNumber(int n) { return (2 * n * n) - n;}
    public static Boolean isHexagonal(int h) {
        double root = Math.sqrt((8.0 * h) + 1);
        return isInteger((root + 1) / 4.0);
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

    public static List<Integer> primeFactors(int n) {
        if (n < 0) { return Collections.emptyList(); }
        List<Integer> results = new ArrayList<>();
        for (int i = 2; i < n; i++) {
            while (n % i == 0) { results.add(i); n /= i; }
        }
        return results;
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

    public static Boolean isPandigital(Long number) {
        int digits = 0;
        int count = 0;
        int expected = 1023;

        if (number < 0) { number = Math.abs(number); }

        if (number < 1023456789) {
            for (; number > 0; number /= 10, count++) {
                if (digits == (digits |= 1 << (number - ((number / 10) * 10) - 1))) { return false; }
            }
            return digits == (1 << count) - 1;
        } else {
            for (; number > 0; number /= 10) {
                digits |= 1 << (number - ((number / 10) * 10));
            }
            return digits == expected;
        }
    }
}