package com.spring.euler.solutions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Problem39 {
    public static Integer run() {
        int result = 0;
        int maxSolutions = 0;
        for (int p = 120; p <= 1000; p += 2) {
            int solutions = 0;
            for (int a = 2; a < p / 3; a++) {
                // given the two equations, (a**2 + b**2 == c**2) and (a + b + c = p), we can rearrange
                // a**2 + b**2 == (p - a - b)**2
                // b = (p**2 - 2pa / (2p - 2a)
                if (p * (p - 2 * a) % (2 * (p - a)) == 0) { solutions++; }
            }
            if (solutions > maxSolutions) {
                result = p;
                maxSolutions = solutions;
            }
        }
        return result;
    }
}
