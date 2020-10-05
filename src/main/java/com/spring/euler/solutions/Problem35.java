package com.spring.euler.solutions;

import com.spring.euler.helper.MathsHelper;
import com.spring.euler.helper.StringHelper;
import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public abstract class Problem35 {
    public static String run() {
        List<List<Integer>> rotations = new ArrayList<>(List.of(List.of(2), List.of(3), List.of(5)));
        for (int i = 7; i <= 1000000; i += 2) {
            if (i % 3 == 0 || i % 5 == 0) { continue; }
            rotations.add(StringHelper.rotations(String.valueOf(i)).stream().map(Integer::parseInt).collect(Collectors.toList()));
        }

        int result =  rotations.stream()
                .filter(rotation -> rotation.stream().allMatch(MathsHelper::isPrime))
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .size();
        return String.valueOf(result);
    }
}
