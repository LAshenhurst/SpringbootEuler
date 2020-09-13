package com.spring.euler.solutions;

import com.spring.euler.helper.FilesHelper;
import com.spring.euler.helper.MathsHelper;
import com.spring.euler.helper.StringHelper;

import java.io.File;
import java.util.Arrays;

public abstract class Problem42 {
    public static long run() {
        File file = FilesHelper.getResourceFile("static/problem42.txt");
        return FilesHelper.readAllLines(file)
                .stream()
                .flatMap(line -> Arrays.stream(line.split(",")))
                .map(word -> word.replace("\"", ""))
                .map(StringHelper::alphabeticalValue)
                .map(MathsHelper::isTriangle)
                .filter(p -> p)
                .count();
    }
}
