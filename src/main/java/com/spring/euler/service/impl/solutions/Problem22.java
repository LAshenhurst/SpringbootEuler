package com.spring.euler.service.impl.solutions;

import com.spring.euler.domain.Alphabet;
import com.spring.euler.helper.FilesHelper;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem22 {
    public static Integer run() {
        File file = FilesHelper.getResourceFile("static/problem22.txt");
        List<String> names = FilesHelper.readAllLines(file)
                .stream()
                .flatMap(line -> Arrays.stream(line.split(","))
                        .map(name -> name.replace("\"", ""))
                )
                .sorted()
                .collect(Collectors.toList());

        return names.stream()
                .map(name -> {
                    int nameScore = 0;
                    String[] nameChars = name.split("");
                    for (String nameChar: nameChars) { nameScore += Alphabet.getIndex(nameChar); }
                    nameScore *= names.indexOf(name) + 1;
                    return nameScore;
                })
                .reduce(0, Integer::sum);
    }
}
