package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.FilesHelper;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Problem18 {
    public static Integer run() {
        File file = FilesHelper.getResourceFile("static/problem18.txt");
        List<List<Integer>> numbers = FilesHelper.readAllLines(file)
                .stream()
                .map(line -> Arrays.stream(line.split(" "))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList())
                )
                .collect(Collectors.toList());

        for (int i = numbers.size() - 2; i >= 0; i--) {
            for (int j = 0; j < numbers.get(i).size(); j++) {
                int maxPath = numbers.get(i).get(j) + Collections.max(List.of(numbers.get(i + 1).get(j), numbers.get(i + 1).get(j + 1)));
                numbers.get(i).set(j, maxPath);
            }
        }
        return numbers.get(0).get(0);
    }
}
