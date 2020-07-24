package com.spring.euler.service.impl.solutions;

import com.spring.euler.common.exception.ApiError;
import com.spring.euler.domain.Alphabet;
import com.spring.euler.helper.FilesHelper;
import com.spring.euler.helper.StringHelper;
import com.spring.euler.helper.MathsHelper;
import org.springframework.http.HttpStatus;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class ThirdSolutions {

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

    private static Integer TwentyThree() {
        Boolean[] abundantNumbers = new Boolean[28124];
        int result = 0;
        for (int i = 0; i < abundantNumbers.length; i++) { abundantNumbers[i] = MathsHelper.isAbundant(i); }
        for (int i = 0; i < abundantNumbers.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (!(abundantNumbers[i] && abundantNumbers[i - j])) { result += i; }
            }
        }
        return result;
    }

    private static String TwentyFour() {
        return StringHelper.permutations("0123456789").get(999999);
    }

    private static Integer TwentyFive() {
        BigInteger x = BigInteger.ONE;
        BigInteger y = BigInteger.ONE;
        BigInteger storage;
        int index = 2;
        while (y.toString().length() != 1000) {
            storage = y;
            y = y.add(x);
            x = storage;
            index++;
        }
        return index;
    }

    private static Integer TwentySix() {
        int result = 0;
        int maxRecurring = 0;
        for (int i = 2; i < 1000; i++) {
            int reciprocal = MathsHelper.recurringCycle(i);
            if (reciprocal > maxRecurring) { result = i; maxRecurring = reciprocal; }
        }
        return result;
    }

    private static Integer TwentySeven() {
        int result = 0;
        int maxN = 0;
        for (int a = -1000; a < 1000; a++) {
            for (int b = -1000; b < 1000; b++) {
                boolean prime = true;
                int n = 0;
                while (prime) {
                    int formula = (n * n) + (a * n) + b;
                    if (!MathsHelper.isPrime(formula)) { prime = false; }
                    n++;
                }
                if (n > maxN) { maxN = n; result = a * b; }
            }
        }
        return result;
    }

    private static Integer TwentyEight() {
        int result = 1;
        for (int i = 1, step = 2; i < (1001 * 1001); i += (step * 4), step += 2) {
            result += (4 * i) + (10 * step);
        }
        return result;
    }

    private static Integer TwentyNine() {
        List<Double> terms = new ArrayList<>();
        for (int a = 2; a <= 100; a++) {
            for (int b = 2; b <= 100; b++) {
                double term = Math.pow(a, b);
                if (!terms.contains(term)) { terms.add(term); }
            }
        }
        return terms.size();
    }

    public static Object getAnswer(int index) {
        Object answer;
        switch (index) {
            case 21 -> answer = TwentyOne();
            case 22 -> answer = TwentyTwo();
            case 23 -> answer = TwentyThree();
            case 24 -> answer = TwentyFour();
            case 25 -> answer = TwentyFive();
            case 26 -> answer = TwentySix();
            case 27 -> answer = TwentySeven();
            case 28 -> answer = TwentyEight();
            case 29 -> answer = TwentyNine();
            default -> throw new ApiError(HttpStatus.NOT_FOUND, "Problem not found");
        }
        return answer;
    }
}
