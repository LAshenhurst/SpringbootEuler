package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem45 {
    public static Integer run() {
        int hex;
        for (int i = 144; ; i++) {
            hex = MathsHelper.hexagonalNumber(i);
            if (MathsHelper.isTriangle(hex) && MathsHelper.isPentagonal(hex)) { return hex; }
        }
    }
}
