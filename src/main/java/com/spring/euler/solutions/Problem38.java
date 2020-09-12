package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public abstract class Problem38 {
    public static Integer run() {
        int result = 0;
        for (int i = 9999; i > 1; i--) {
            for (int j = 2; ; j++) {
                int finalI = i;
                String concat = IntStream.rangeClosed(1, j).boxed().map(x -> String.valueOf(x * finalI)).collect(Collectors.joining(""));
                if (concat.length() < 9) { continue; } else if (concat.length() > 9) { break; }
                if (MathsHelper.isPandigital(concat)) { result = Math.max(result, Integer.parseInt(concat)); }
            }
        }
        return result;
    }
}
