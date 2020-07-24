package com.spring.euler.service.impl.solutions;

public abstract class Problem28 {
    public static Integer run() {
        int result = 1;
        for (int i = 1, step = 2; i < (1001 * 1001); i += (step * 4), step += 2) {
            result += (4 * i) + (10 * step);
        }
        return result;
    }
}
