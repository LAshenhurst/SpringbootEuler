package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem12 {
    public static String run() {
        for (int i = 1; ; i++) {
            Integer triangleNumber = MathsHelper.triangleNumber(i);
            if (MathsHelper.findDivisors(triangleNumber).size() > 500) { return String.valueOf(triangleNumber); }
        }
    }
}
