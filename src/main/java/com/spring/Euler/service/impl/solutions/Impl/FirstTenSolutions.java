package com.spring.Euler.service.impl.solutions.Impl;

import com.spring.Euler.helper.BigIntegerHelper;
import com.spring.Euler.helper.FilesHelper;
import com.spring.Euler.helper.MathsHelper;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

public interface FirstTenSolutions {
    default Integer One() { return IntStream.rangeClosed(0, 999).filter(x -> x % 3 == 0 || x % 5 == 0).sum(); }

    default Integer Two() {
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

    default long Three() {
        long result = 600851475143L;
        int prime = 2;
        while (!MathsHelper.isPrime(result)) {
            if (result % prime == 0) { result /= prime; } else { prime = MathsHelper.findNextPrime(prime); }
        }
        return result;
    }

    default Integer Four() {
        int result = -1;
        for (int i = 100; i < 1000; i++) {
            for (int j = 100; j < 1000; j++) {
                if (MathsHelper.isPalindrome(i * j) && i * j > result) { result = i * j; }
            }
        }
        return result;
    }

    default BigInteger Five() {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= 20; i++) { result = BigIntegerHelper.lowestCommonMultiple(BigInteger.valueOf(i), result); }
        return result;
    }

    default long Six() {
        long sumOfSquares = IntStream.rangeClosed(1, 100).map(x -> x * x).sum();
        long squareOfSums = (long) Math.pow(IntStream.rangeClosed(1, 100).sum(), 2);
        return Math.abs(squareOfSums - sumOfSquares);
    }

    default Integer Seven() {
        int prime = 2;
        for(int count = 1; count < 10001; count++) { prime = MathsHelper.findNextPrime(prime); }
        return prime;
    }

    default Long Eight() {
        File file = FilesHelper.getResourceFile("static/problem8.txt");
        String number = String.join("", FilesHelper.readAllLines(file));
        Long result = 0L;
        for (int count = 13; count < number.length(); count++) {
            Long product = List.of(number.substring(count - 13, count).split(""))
                    .stream()
                    .map(Long::parseLong)
                    .reduce(1L, (a, b) -> a * b);
            if (product > result) { result = product; }
        }
        return result;
    }
}
