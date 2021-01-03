package com.spring.euler.solutions;

import com.spring.euler.helper.StringHelper;

public final class Problem24 {
    public static String run() {
        return StringHelper.permutations("0123456789").get(999999);
    }
}