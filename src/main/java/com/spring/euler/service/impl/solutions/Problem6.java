package com.spring.euler.service.impl.solutions;

import java.util.stream.IntStream;

public abstract class Problem6 {
    public static Long run() {
        long sumOfSquares = IntStream.rangeClosed(1, 100).map(x -> x * x).sum();
        long squareOfSums = (long) Math.pow(IntStream.rangeClosed(1, 100).sum(), 2);
        return Math.abs(squareOfSums - sumOfSquares);
    }
}
