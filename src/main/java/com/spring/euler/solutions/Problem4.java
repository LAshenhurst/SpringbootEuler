package com.spring.euler.solutions;

import com.spring.euler.helper.StringHelper;

public final class Problem4 {
    public static String run() {
        int result = -1;
        for (int i = 100; i < 1000; i++) {
            for (int j = 100; j < 1000; j++) {
                if (StringHelper.isPalindrome(String.valueOf(i * j)) && i * j > result) { result = i * j; }
            }
        }
        return String.valueOf(result);
    }
}
