package com.spring.euler.service.impl.solutions;

import java.util.stream.IntStream;

public abstract class Problem1 {
    public static Integer run() {
        return IntStream.rangeClosed(0, 999).filter(x -> x % 3 == 0 || x % 5 == 0).sum();
    }
}