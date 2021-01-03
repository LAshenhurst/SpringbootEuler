package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public final class Problem44 {
    public static String run() {
        int k; int j;
        int result = -1;
        for (int i = 1; result == -1; i++) {
            k = MathsHelper.pentagonalNumber(i);
            for (int a = i - 1; a > 0; a--)
            {
                j = MathsHelper.pentagonalNumber(a);
                if (MathsHelper.isPentagonal(k + j) && MathsHelper.isPentagonal(k - j)) { result = k - j; }
            }
        }
        return String.valueOf(result);
    }
}