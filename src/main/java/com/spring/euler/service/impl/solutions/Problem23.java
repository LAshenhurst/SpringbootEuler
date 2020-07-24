package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.MathsHelper;

public class Problem23 {
    public static Integer run() {
        Boolean[] abundantNumbers = new Boolean[28124];
        int result = 0;
        for (int i = 0; i < abundantNumbers.length; i++) { abundantNumbers[i] = MathsHelper.isAbundant(i); }
        for (int i = 0; i < abundantNumbers.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (!(abundantNumbers[i] && abundantNumbers[i - j])) { result += i; }
            }
        }
        return result;
    }
}
