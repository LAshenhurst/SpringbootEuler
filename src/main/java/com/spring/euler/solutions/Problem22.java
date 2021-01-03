package com.spring.euler.solutions;

import com.spring.euler.domain.Alphabet;
import com.spring.euler.helper.FilesHelper;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class Problem22 {
    public static String run() {
        File file = FilesHelper.getResourceFile("static/problem22.txt");
        List<String> names = FilesHelper.readAllLines(file)
                .stream()
                .flatMap(line -> Arrays.stream(line.split(","))
                        .map(name -> name.replace("\"", ""))
                )
                .sorted()
                .collect(Collectors.toList());

        int result = names.stream()
                .map(name -> {
                    int nameScore = 0;
                    String[] nameChars = name.split("");
                    for (String nameChar: nameChars) { nameScore += Alphabet.getIndex(nameChar); }
                    nameScore *= names.indexOf(name) + 1;
                    return nameScore;
                })
                .reduce(0, Integer::sum);

        return String.valueOf(result);
    }
}
