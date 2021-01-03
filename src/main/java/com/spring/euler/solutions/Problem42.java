package com.spring.euler.solutions;

import com.spring.euler.helper.FilesHelper;
import com.spring.euler.helper.MathsHelper;
import com.spring.euler.helper.StringHelper;

import java.io.File;
import java.util.Arrays;

public final class Problem42 {
    public static String run() {
        File file = FilesHelper.getResourceFile("static/problem42.txt");
        long result =  FilesHelper.readAllLines(file)
                .stream()
                .flatMap(line -> Arrays.stream(line.split(",")))
                .map(word -> word.replace("\"", ""))
                .map(StringHelper::alphabeticalValue)
                .map(MathsHelper::isTriangle)
                .filter(p -> p)
                .count();

        return String.valueOf(result);
    }
}
