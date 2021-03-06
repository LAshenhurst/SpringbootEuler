package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Problem38 {
    public static String run() {
        int result = 0;
        for (int i = 9999; i > 1; i--) {
            for (int j = 2; ; j++) {
                int finalI = i;
                String concat = IntStream.rangeClosed(1, j).boxed().map(x -> String.valueOf(x * finalI)).collect(Collectors.joining(""));
                if (concat.length() < 9) { continue; } else if (concat.length() > 9) { break; }
                if (MathsHelper.isPandigital(Long.parseLong(concat))) { result = Math.max(result, Integer.parseInt(concat)); }
            }
        }
        return String.valueOf(result);
    }
}
