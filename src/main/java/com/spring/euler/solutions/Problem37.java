package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;
import java.util.HashSet;

@Slf4j
public abstract class Problem37 {
    public static String run() {
        int result = 0;
        int count = 0;
        Set<Integer> truncateValues = new HashSet<>();
        for (int i = 11; count < 11; i += 2) {
            if (!MathsHelper.isPrime(i)) { continue; }
            truncateValues.clear();
            for (int j = 0; ; j++) {
                int rightTruncate =  i / (int) Math.pow(10, j);
                int leftTruncate = i % (int) Math.pow(10, j);
                if (rightTruncate > 0) { truncateValues.add(rightTruncate); }
                if (leftTruncate > 0) { truncateValues.add(leftTruncate); }
                if (rightTruncate == 0 ) { break; }
            }
            if (truncateValues.stream().allMatch(MathsHelper::isPrime)) { result += i; count++; }
        }
        return String.valueOf(result);
    }
}
