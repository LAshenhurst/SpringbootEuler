package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.StringHelper;

public abstract class Problem24 {
    public static String run() {
        return StringHelper.permutations("0123456789").get(999999);
    }
}