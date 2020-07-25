package com.spring.euler.solutions;

import com.spring.euler.helper.StringHelper;

import java.util.stream.IntStream;

public abstract class Problem36 {
    public static Integer run() {
        return IntStream.rangeClosed(0, 1000000)
                .boxed()
                .filter(x -> StringHelper.isPalindrome(String.valueOf(x)) && StringHelper.isPalindrome(Integer.toString(x, 2)))
                .reduce(0, Integer::sum);
    }
}
