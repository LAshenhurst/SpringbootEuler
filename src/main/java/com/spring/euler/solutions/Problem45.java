package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public final class Problem45 {
    public static String run() {
        int hex;
        for (int i = 144; ; i++) {
            hex = MathsHelper.hexagonalNumber(i);
            if (MathsHelper.isTriangle(hex) && MathsHelper.isPentagonal(hex)) { return String.valueOf(hex); }
        }
    }
}
