package com.spring.Euler.service.impl.solutions;

import com.spring.Euler.common.ApiError;
import com.spring.Euler.domain.Alphabet;
import com.spring.Euler.helper.FilesHelper;
import com.spring.Euler.helper.MathsHelper;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThirdSolutions {

    private static Integer TwentyOne() {
        List<Integer> amicableNumbers = new ArrayList<>();
        for (int i = 1; i < 10000; i++) {
            if (amicableNumbers.contains(i)) { continue; }
            int iDivisors = MathsHelper.findDivisors(i).stream().reduce(0, Integer::sum) - i;
            int jDivisors = MathsHelper.findDivisors(iDivisors).stream().reduce(0, Integer::sum) - iDivisors;
            if (i != iDivisors && i == jDivisors) { amicableNumbers.addAll(List.of(i, iDivisors)); }
        }
        return amicableNumbers.stream().reduce(0, Integer::sum);
    }

    private static Integer TwentyTwo() {
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

    public static String getAnswer(int index) {
        Object answer;
        switch (index) {
            case 21 -> answer = TwentyOne();
            case 22 -> answer = TwentyTwo();
            default -> throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found");
        }
        return answer.toString();
    }
}
