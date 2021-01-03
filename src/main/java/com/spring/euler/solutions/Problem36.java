package com.spring.euler.solutions;

import com.spring.euler.helper.StringHelper;

import java.util.stream.IntStream;

public final class Problem36 {
    public static String run() {
        int result =  IntStream.rangeClosed(0, 1000000)
                .filter(x -> StringHelper.isPalindrome(String.valueOf(x)) && StringHelper.isPalindrome(Integer.toString(x, 2)))
                .reduce(0, Integer::sum);
        return String.valueOf(result);
    }
}
