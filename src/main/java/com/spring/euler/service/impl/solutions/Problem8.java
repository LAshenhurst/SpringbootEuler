package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.FilesHelper;

import java.io.File;
import java.util.List;

public abstract class Problem8 {
    public static Long run() {
        File file = FilesHelper.getResourceFile("static/problem8.txt");
        String number = String.join("", FilesHelper.readAllLines(file));
        Long result = 0L;
        for (int count = 13; count < number.length(); count++) {
            Long product = List.of(number.substring(count - 13, count).split(""))
                    .stream()
                    .map(Long::parseLong)
                    .reduce(1L, (a, b) -> a * b);
            if (product > result) { result = product; }
        }
        return result;
    }
}
