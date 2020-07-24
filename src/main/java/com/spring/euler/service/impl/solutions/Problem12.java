package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.MathsHelper;

public class Problem12 {
    public static Integer run() {
        for (int i = 1; ; i++) {
            Integer triangleNumber = MathsHelper.triangleNumber(i);
            if (MathsHelper.findDivisors(triangleNumber).size() > 500) { return triangleNumber; }
        }
    }
}
