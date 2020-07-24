package com.spring.euler.service.impl.solutions;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.helper.BigIntegerHelper;
import com.spring.euler.helper.FilesHelper;
import com.spring.euler.helper.MathsHelper;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public final class SecondSolutions {
    private static Integer Eleven() {
        File file = FilesHelper.getResourceFile("static/problem11.txt");
        Integer[][] square = FilesHelper.readAllLines(file)
                .stream()
                .map(line -> List.of(line.split(" "))
                        .stream()
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new))
                .toArray(Integer[][]::new);
        int result = -1;
        for (int y = 0; y < square.length; y++) {
            for (int x = 0; x < square[y].length; x++) {
                result = Math.max(product(square, x, y, 1, 0, 4), result);
                result = Math.max(product(square, x, y, -1, 0, 4), result);
                result = Math.max(product(square, x, y, 0, 1, 4), result);
                result = Math.max(product(square, x, y, 0, -1, 4), result);
                result = Math.max(product(square, x, y, 1, 1, 4), result);
                result = Math.max(product(square, x, y, -1, -1, 4), result);
                result = Math.max(product(square, x, y, 1, -1, 4), result);
                result = Math.max(product(square, x, y, -1, 1, 4), result);
            }
        }
        return result;
    }

    private static int product(Integer[][] square, int x, int y, int dx, int dy, int n) {
        if (!inBounds(x + (n - 1) * dx, y + (n - 1) * dy, square.length)) { return -1; }

        int prod = 1;
        for (int i = 0; i < n; i++, x += dx, y += dy) { prod *= square[y][x]; }
        return prod;
    }

    private static Boolean inBounds(int x, int y, int squareSize) {
        return y >= 0 && y < squareSize && x >= 0 && x < squareSize;
    }

    private static Integer Twelve() {
        for (int i = 1; ; i++) {
            Integer triangleNumber = MathsHelper.triangleNumber(i);
            if (MathsHelper.findDivisors(triangleNumber).size() > 500) { return triangleNumber; }
        }
    }

    private static String Thirteen() {
        File file = FilesHelper.getResourceFile("static/problem13.txt");
        List<String> numbers = FilesHelper.readAllLines(file);
        BigInteger result = BigInteger.ZERO;
        for (String number: numbers) { result = result.add(new BigInteger(number)); }
        return result.toString().substring(0, 10);
    }

    private static Long Fourteen() {
        long result = -1L;
        int maxCollatz = -1;
        for (long i = 1L; i < 1000000; i++) {
            long collatzValue = i;
            int collatzChain = 0;
            while (collatzValue != 1) {
                collatzValue = MathsHelper.collatz(collatzValue);
                collatzChain++;
                if (MathsHelper.isInteger(MathsHelper.log2(collatzValue))) {
                    collatzChain += MathsHelper.log2(collatzValue).intValue();
                    collatzValue = 1L;
                }
            }
            if (collatzChain > maxCollatz) {
                result = i;
                maxCollatz = collatzChain;
            }
        }
        return result;
    }

    private static Long Fifteen() {
        return MathsHelper.binomial(40, 20);
    }

    private static Integer Sixteen() {
        return Arrays.stream(BigInteger.TWO.pow(1000).toString().split(""))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    private static Integer Seventeen() {
        String[] digits = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] teens = {"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        int result = 11;
        for (int i = 0; i <= 9; i++) {
            if (i > 0) { result += (digits[i - 1].length() + 7) + ((digits[i - 1].length() + 10) * 99); }
            for (int j = 1; j < 100; j++) {
                if (j < 10) { result += digits[j - 1].length(); }
                else if (j % 10 == 0) { result += tens[(j / 10) - 1].length(); }
                else if (j < 20) { result += teens[(j / 10) - 1].length(); }
                else { result += tens[(j / 10) - 1].length() + digits[(j % 10) - 1].length(); }
            }
        }
        return result;
    }

    private static Integer Eighteen() {
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

    private static Integer Nineteen() {
        int result = 0;
        Calendar date = Calendar.getInstance();
        date.set(1901, Calendar.JANUARY, 1);

        while (date.get(Calendar.YEAR) < 2001) {
            date.add(Calendar.MONTH, 1);
            if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) { result++; }
        }

        return result;
    }

    private static Integer Twenty() {
        return Arrays.stream(BigIntegerHelper.factorial(100).toString().split(""))
                .map(Integer::parseInt)
                .reduce(0, Integer::sum);
    }

    public static Object getAnswer(int index) throws ApiError {
        Object answer;
        switch (index) {
            case 11 -> answer = Eleven();
            case 12 -> answer = Twelve();
            case 13 -> answer = Thirteen();
            case 14 -> answer = Fourteen();
            case 15 -> answer = Fifteen();
            case 16 -> answer = Sixteen();
            case 17 -> answer = Seventeen();
            case 18 -> answer = Eighteen();
            case 19 -> answer = Nineteen();
            case 20 -> answer = Twenty();
            default -> throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found");
        }
        return answer;
    }
}