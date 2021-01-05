package com.spring.euler.solutions;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public final class Problem62 {
    public static String run() {
        List<Long> cubes = new ArrayList<>();

        for (long i = 1; i < 10000 ; i++) {
            long key = sortDigits(i * i * i);
            cubes.add(key);

            long cubeOccurrences = cubes.stream()
                    .reduce(0L, (count, current) -> count + (current.equals(key) ? 1 : 0));

            if (cubeOccurrences == 5) {
                long index = cubes.indexOf(key) + 1;
                return String.valueOf(index * index * index);
            }
        }
        return null;
    }

    private static Long sortDigits(long n) {
        List<Character> test = CollectionUtils.arrayToList(String.valueOf(n).toCharArray());
        Collections.sort(test);
        Collections.reverse(test);
        String result = test.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        return Long.parseLong(result);
    }
}
