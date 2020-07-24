package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem4 {
    public static Integer run() {
        int result = -1;
        for (int i = 100; i < 1000; i++) {
            for (int j = 100; j < 1000; j++) {
                if (MathsHelper.isPalindrome(i * j) && i * j > result) { result = i * j; }
            }
        }
        return result;
    }
}
