package com.spring.euler.service.impl.solutions;

import com.spring.euler.helper.FilesHelper;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

public class Problem13 {
    public static String run() {
        File file = FilesHelper.getResourceFile("static/problem13.txt");
        List<String> numbers = FilesHelper.readAllLines(file);
        BigInteger result = BigInteger.ZERO;
        for (String number: numbers) { result = result.add(new BigInteger(number)); }
        return result.toString().substring(0, 10);
    }
}