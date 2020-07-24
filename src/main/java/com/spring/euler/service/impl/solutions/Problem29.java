package com.spring.euler.service.impl.solutions;

import java.util.ArrayList;
import java.util.List;

public abstract class Problem29 {
    public static Integer run() {
        List<Double> terms = new ArrayList<>();
        for (int a = 2; a <= 100; a++) {
            for (int b = 2; b <= 100; b++) {
                double term = Math.pow(a, b);
                if (!terms.contains(term)) { terms.add(term); }
            }
        }
        return terms.size();
    }
}
