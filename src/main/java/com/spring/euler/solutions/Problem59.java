package com.spring.euler.solutions;

import com.spring.euler.helper.FilesHelper;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Problem59 {
    private static final File FILE = FilesHelper.getResourceFile("static/Problem59.txt");
    private static final List<Integer> VALUES = FilesHelper.readAllLines(FILE).stream()
            .flatMap(line -> Arrays.stream(line.split(",")))
            .map(Integer::parseInt)
            .collect(Collectors.toList());

    public static String run() {
        return String.valueOf(decrypt(findKey())
                .stream()
                .reduce(0, Integer::sum));
    }

    private static int[] findKey() {
        int maxsize = Collections.max(VALUES);

        int[][] splitFrequencies = new int[3][maxsize+1];
        int[] key = new int[3];

        for (int i = 0; i < VALUES.size(); i++) {
            int j = i % 3;
            splitFrequencies[j][VALUES.get(i)]++;
            if (splitFrequencies[j][VALUES.get(i)] > splitFrequencies[j][key[j]]) {
                key[j] = VALUES.get(i);
            }
        }

        int spaceAscii = 32;
        for (int i = 0; i < 3; i++) { key[i] = key[i] ^ spaceAscii; }
        return key;
    }

    private static List<Integer> decrypt(int[] key) {
        List<Integer> plaintext = new ArrayList<>();
        for (int i = 0; i < VALUES.size(); i++)
            plaintext.add(VALUES.get(i) ^ key[i % key.length]);
        return plaintext;
    }
}
