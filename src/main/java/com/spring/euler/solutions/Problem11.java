package com.spring.euler.solutions;

import com.spring.euler.helper.FilesHelper;

import java.io.File;
import java.util.List;

public abstract class Problem11 {
    public static String run() {
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
        return String.valueOf(result);
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
}
