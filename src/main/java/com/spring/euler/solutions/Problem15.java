package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

public abstract class Problem15 {
    public static Long run() {
        return MathsHelper.binomial(40, 20);
    }
}